Ext.define("core.servicemanage.bpelmanage.view.UserGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.usergrid",
    title: "<center height=40>人员信息</center>",
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
        items: [
            //    {
            //    xtype: 'textfield',
            //    fieldLabel: '用户id',
            //    labelWidth: 48,
            //    width: 120,
            //    name: 'uid'
            //}, {
            //    xtype: 'textfield',
            //    fieldLabel: '用户名',
            //    labelWidth: 50,
            //    width: 120,
            //    name: 'account'
            //}, {
            //    xtype: 'button',
            //    text: '查询',
            //    iconCls: 'search',
            //    ref: 'searchPeople'
            //},
            {
                xtype: 'button',
                text: '解禁',
                iconCls: 'search',
                ref: 'enable'
            }
        ]
    }],
    columns: [{
        width: 80,
        height: 36,
        dataIndex: "uid",
        text: 'uid',
        align: 'center'
    }, {
        text: "账号",
        dataIndex: "account",
        width: 200,
        align: 'center'
    }, {
        text: "昵称",
        dataIndex: "nickName",
        width: 140,
        align: 'center'
    }],
    store: "core.servicemanage.bpelmanage.store.UserStore",
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
        store: "core.servicemanage.bpelmanage.store.UserStore",
        displayInfo: true,
        flex: 1
    }]

});