package com.ityu.mall.eth;


import com.google.common.base.Strings;
import com.ityu.mall.model.InputModel;
import com.ityu.mall.model.Triple2;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


import static org.web3j.tx.Transfer.GAS_LIMIT;


public class EthUtils {
    /**
     * 通过私钥获取凭证
     *
     * @param privateKey
     * @return
     */
    public static Credentials getCreFromPrivateKey(String privateKey) {
        return Credentials.create(privateKey);
    }

    public static ClientTransactionManager getCreFrom(String address, int isTest) {
        return new ClientTransactionManager(Web3JClient.INSTANCE.getWeb3j(isTest), address);
    }

    public static TransactionReceipt sendETH(Credentials credentials, String toaddress, String count, int isTest) throws Exception {
        TransactionReceipt transactionReceipt = Transfer
                .sendFunds(Web3JClient.INSTANCE.getWeb3j(isTest), credentials, toaddress, BigDecimal.valueOf(Double.valueOf(count)), Convert.Unit.ETHER).sendAsync().get();
        return transactionReceipt;
    }

    public static EthSendTransaction sendCustomETH(Credentials credentials, String toaddress, String count, int isTest) throws Exception {
        // get the next available nonce
        EthGetTransactionCount ethGetTransactionCount = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        BigInteger price = Web3JClient.INSTANCE.getWeb3j(isTest).ethGasPrice().send().getGasPrice();
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, price, GAS_LIMIT, toaddress, Convert.toWei(count, Convert.Unit.ETHER).toBigInteger());
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = "0x" + Hex.toHexString(signedMessage);
        return Web3JClient.INSTANCE.getWeb3j(isTest).ethSendRawTransaction(hexValue).send();
    }


    public static TransactionReceipt sendToken(Credentials credentials, String toaddress, String count, String contractAddress, double decimals, int isTest) throws Exception {
        Erc20Token2 load = Erc20Token2.load(contractAddress, Web3JClient.INSTANCE.getWeb3j(isTest), credentials, new DefaultGasProvider());
        CompletableFuture<TransactionReceipt> abb = load.transfer(toaddress, new BigDecimal(count).multiply(new BigDecimal(Math.pow(10, decimals))).toBigInteger()).sendAsync();
        return abb.get();
    }

    public static TransactionReceipt sendCustomToken(Credentials credentials, String toaddress, String count, String contractAddress, double decimals, int isTest) throws Exception {
        return getTransactionReceipt(credentials, toaddress, count, contractAddress, new StaticGasProvider(DefaultGasProvider.GAS_PRICE.divide(new BigInteger("2")), new BigInteger("90000")), decimals, isTest);
    }

    public static TransactionReceipt sendCustomTokenFast(Credentials credentials, String toaddress, String count, String contractAddress, double decimals, int isTest) throws Exception {
        return getTransactionReceipt(credentials, toaddress, count, contractAddress, new DefaultGasProvider(), decimals, isTest);
    }


    public static TransactionReceipt getTransactionReceipt(Credentials credentials, String toaddress, String count, String contractAddress, StaticGasProvider provider, double decimals, int isTest) throws Exception {
        NoOpProcessor processor = new NoOpProcessor(Web3JClient.INSTANCE.getWeb3j(isTest));
        TransactionManager txManager = new FastRawTransactionManager(Web3JClient.INSTANCE.getWeb3j(isTest), credentials, processor);
        Erc20Token2 token = Erc20Token2.load(contractAddress, Web3JClient.INSTANCE.getWeb3j(isTest), txManager, provider);
        return token.transfer(toaddress, new BigDecimal(count).multiply(new BigDecimal(Math.pow(10, decimals))).toBigInteger()).send();
    }


    public static Triple2<String, String> getErrMsg(String address, String priKey) {
        if (!WalletUtils.isValidAddress(address)) {
            return new Triple2<>("地址不合法", "");
        }
        try {
            priKey = AESCipher.INSTANCE.decryptAES(priKey);
        } catch (Exception e) {
            e.printStackTrace();
            return new Triple2<>("私钥不合法", "");
        }
        if (!WalletUtils.isValidPrivateKey(priKey)) {
            return new Triple2<>("私钥不合法", "");
        }
        return new Triple2<>("", priKey);
    }


    public static Erc20Token2 getToken(Credentials credentials, String contractAddress, int isTest) {
        Erc20Token2 load = Erc20Token2.load(contractAddress, Web3JClient.INSTANCE.getWeb3j(isTest), credentials, new DefaultGasProvider());
        return load;
    }

    public static Erc20Token2 getToken(String address, String contractAddress, int isTest) {
        Erc20Token2 load = Erc20Token2.load(contractAddress, Web3JClient.INSTANCE.getWeb3j(isTest), getCreFrom(address, isTest), new DefaultGasProvider());
        return load;
    }


    public static String getBalnace(String address, int isTest) {
        try {
            BigInteger balance = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get().getBalance();
            return Convert.fromWei(balance + "", Convert.Unit.ETHER) + "";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public static String getStatus(String status1) {
        String status = "0";
        if (!Strings.isNullOrEmpty(status1)) {
            status = status1.substring(2);
            System.out.println(status);
        }
        return status;
    }

    public static String getStatus2(String txhash, int isTest) {
        String status = "0";
        try {
            EthGetTransactionReceipt ethGetTransactionReceipt = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetTransactionReceipt(txhash).sendAsync().get();
            String status1 = ethGetTransactionReceipt.getTransactionReceipt().get().getStatus();
            if (!Strings.isNullOrEmpty(status1)) {
                status = status1.substring(2);
                System.out.println(status);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;
    }


    public static EthBlock getTxs(int isTest) {
        try {
            EthBlock ethBlockRequest = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send();
            return ethBlockRequest;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }

    }

    public static EthBlock getTxsByNum(String blockNum, int isTest) {
        try {
            EthBlock ethBlockRequest = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetBlockByNumber(DefaultBlockParameter.valueOf(new BigInteger(blockNum)), true).send();
            return ethBlockRequest;
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }

    }


    public static Transaction getDetailByHash(String txhash, int isTest) {
        CompletableFuture<EthTransaction> ethGetTransactionReceipt = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetTransactionByHash(txhash).sendAsync();
        try {
            Transaction transaction = ethGetTransactionReceipt.get().getTransaction().get();
            return transaction;
        } catch (Exception e) {
            return null;
        }
    }


    public static TransactionReceipt getDetailByHash2(String txhash, int isTest) {
        try {
            EthGetTransactionReceipt ethGetTransactionReceipt = Web3JClient.INSTANCE.getWeb3j(isTest).ethGetTransactionReceipt(txhash).sendAsync().get();
            TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
            return transactionReceipt;
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) {

        try {
//            TransactionReceipt send = load().approve("0x40ae7e90f36c75ad5c999cd2efa8f594625b8423",
//                            toNum("1", 8)).send();
//            System.out.println(send.getTransactionHash());

            long l = load().allowance("0x5719913C69f0B5EE82F3136bCAcba9BdB6098b53", "0x40ae7e90f36c75ad5c999cd2efa8f594625b8423").send().longValue();

            System.out.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  static Erc20Token2 load(){
       return Erc20Token2.load("0x1b86873ddfeb15a5dbf329ef3e9f7d3d97aaa2c7",
                Web3JClient.INSTANCE.getWeb3j(1), Credentials.create("C26039B3F1DBFA950A12160B1C65492A418516FC6FB5F4B88807F8466B5583E0"), new DefaultGasProvider());
    }

    public static BigInteger toNum(String num,int cifang){
       return new BigDecimal(num).multiply(new BigDecimal(BigInteger.TEN.pow(cifang))).toBigInteger();
    }
    public static InputModel parseInputData(String inputData) {
        String method = inputData.substring(0, 10);
        String to = inputData.substring(10, 74);
        String value = inputData.substring(74);
        Method refMethod = null;
        try {
            refMethod = TypeDecoder.class.getDeclaredMethod("decode", String.class, int.class, Class.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        refMethod.setAccessible(true);
        Address address = null;
        try {
            address = (Address) refMethod.invoke(null, to, 0, Address.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Uint256 amount = null;
        try {
            amount = (Uint256) refMethod.invoke(null, value, 0, Uint256.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new InputModel(method, value, to, amount.getValue().toString(), address.getValue());
    }


    public static Triple2<Boolean, Long> getBlockNum(int isTest) {
        try {
            return new Triple2(true, Web3JClient.INSTANCE.getWeb3j(isTest).ethBlockNumber().send().getBlockNumber().longValue());
        } catch (IOException e) {
            //  e.printStackTrace();
            return new Triple2(false, 0l);
        }
    }

    //https://walletapi.bwinchain.io bwin
    //https://walletapi.vblockchain.co  vbc


    public static String ethEstimateGasEth(int isTest) {
        BigInteger price = new BigInteger("0");
        try {
            price = Web3JClient.INSTANCE.getWeb3j(isTest).ethGasPrice().send().getGasPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Convert.fromWei(price.multiply(GAS_LIMIT).toString(), Convert.Unit.ETHER).toPlainString();
    }


}
