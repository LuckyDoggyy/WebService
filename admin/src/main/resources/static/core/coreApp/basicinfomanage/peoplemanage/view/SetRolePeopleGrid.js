Ext.define("core.basicinfomanage.peoplemanage.view.SetRolePeopleGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.setrolepeoplegrid",
    title: "<center height=40>人员角色设置</center>",
    selModel: {
        selType: "checkboxmodel",
        mode: 'SIMPLE'
    },
    multiSelect: true,
    columnLines: true,
    loadMask: {
        msg: "数据加载中，请稍等..."
    },
    autoScroll: true,
    disableSelection: false,
    enableKeyNav: true,
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            xtype: 'textfield',
            fieldLabel: '用户id',
            labelWidth: 48,
            width: 120,
            name: 'uid'
        }, {
            xtype: 'textfield',
            fieldLabel: '账户',
            labelWidth: 50,
            width: 120,
            name: 'account'
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'search',
            ref: 'searchPeople'
        }]
    }, {
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            xtype: 'button',
            text: '设置角色',
            ref: 'checkRole',
            disabled: true,
            hidden: false
        }]
    }],
    columns: [{
        width: 50,
        height: 36,
        dataIndex: "uid",
        text: 'uid',
        align: 'center'
    }, {
        text: "账号",
        dataIndex: "account",
        width: 140,
        align: 'center'
    }, {
        text: "昵称",
        dataIndex: "nickName",
        width: 140,
        align: 'center'
    }, {
        text: "备注",
        dataIndex: "remarks",
        width: 240,
        align: 'center'
    }],
    store: "core.basicinfomanage.peoplemanage.store.PeopleStore",
    bbar: [{
        xtype: 'button',
        text: '全选',
        handler: function (button, e) {
            button.ownerCt.ownerCt.getSelectionModel().selectAll();
        }
    }, {
        xtype: 'button',
        text: '取消',
        handler: function (button, e) {
            button.ownerCt.ownerCt.getSelectionModel().deselectAll();
        }
    }, {
        xtype: 'pagingtoolbar',
        store: "core.basicinfomanage.peoplemanage.store.PeopleStore",
        displayInfo: true,
        flex: 1
    }]

});