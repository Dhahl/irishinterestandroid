package com.irishinterest.irishinterest.network.api.irishInterest.observer

import com.irishinterest.irishinterest.network.api.irishInterest.observer.InternalAction
import com.irishinterest.irishinterest.network.api.irishInterest.observer.ScrollAction

class GuiMessage<T : Data?> {
    var module: Module
        private set
    var action: Action
        private set
    var internalAction: InternalAction? = null
        private set
    var `object`: T? = null
        private set
    var dataList: List<T>? = null
        private set
    var scrollAction: ScrollAction? = null
        private set

    constructor(module: Module, action: Action) {
        this.module = module
        this.action = action
    }

    constructor(module: Module, action: Action, internalAction: InternalAction?) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
    }

    constructor(module: Module, action: Action, internalAction: InternalAction?, `object`: T) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.`object` = `object`
    }

    constructor(
        module: Module,
        action: Action,
        internalAction: InternalAction?,
        scrollAction: ScrollAction?,
        `object`: T
    ) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.`object` = `object`
        this.scrollAction = scrollAction
    }

    constructor(
        module: Module,
        action: Action,
        internalAction: InternalAction?,
        dataList: List<T>?
    ) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.dataList = dataList
    }

    constructor(
        module: Module,
        action: Action,
        internalAction: InternalAction?,
        scrollAction: ScrollAction?,
        dataList: List<T>?
    ) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.dataList = dataList
        this.scrollAction = scrollAction
    }

    constructor(
        module: Module,
        action: Action,
        internalAction: InternalAction?,
        `object`: T,
        dataList: List<T>?
    ) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.`object` = `object`
        this.dataList = dataList
    }

    constructor(
        module: Module,
        action: Action,
        internalAction: InternalAction?,
        `object`: T,
        dataList: List<T>?,
        scrollAction: ScrollAction?
    ) {
        this.module = module
        this.action = action
        this.internalAction = internalAction
        this.`object` = `object`
        this.dataList = dataList
        this.scrollAction = scrollAction
    }
}