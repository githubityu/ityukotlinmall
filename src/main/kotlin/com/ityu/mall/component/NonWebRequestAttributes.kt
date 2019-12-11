package com.ityu.mall.component

import org.springframework.web.context.request.RequestAttributes

class NonWebRequestAttributes: RequestAttributes {
    override fun getAttribute(name: String, scope: Int): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAttribute(name: String, value: Any, scope: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAttribute(name: String, scope: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSessionMutex(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSessionId(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAttributeNames(scope: Int): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerDestructionCallback(name: String, callback: Runnable, scope: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resolveReference(key: String): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}