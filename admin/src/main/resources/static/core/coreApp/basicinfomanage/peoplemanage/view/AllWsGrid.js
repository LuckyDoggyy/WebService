Ext.define("core.basicinfomanage.peoplemanage.view.AllWsGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.allwsgrid",
    title: "<center height=40>全部服务管理</center>",
    selModel: {
        selType: "checkboxmodel",
        mode: 'SIMPLE'
    },
    multiSelect: true,
    columnLines: true, // 展示竖线
    loadMask: {
        msg: "数据加载中，请稍等..."
    },
    autoScroll: true,
    disableSelection: false,
    enableKeyNav: true, // 可以使用键盘控制上下

    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            xtype: 'textfield',
            fieldLabel: '服务编号',
            name: 'sid',
            labelWidth: 65,
            width: 135
        }, {
            xtype: 'textfield',
            fieldLabel: '服务名称',
            name: 'sname',
            labelWidth: 65,
            width: 135
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'search',
            ref: 'searchallWs'
        }, {
            xtype: 'button',
            text: '添加到左侧用户',
            ref: 'addforUser'
        }]
    }],

    columns: [{
        text: "服务号",
        dataIndex: "sid",
        align: 'center',
        width: 60
    }, {
        text: "服务名",
        dataIndex: "name",
        align: 'center',
        width: 120
    }, {
        text: "ip",
        dataIndex: "ip",
        align: 'center',
        width: 120
    }, {
        text: "端口",
        dataIndex: "port",
        align: 'center',
        width: 50
    }, {
        text: "备注信息",
        dataIndex: "remarks",
        align: 'center',
        width: 140
    }],
    store: "core.basicinfomanage.wsmanage.store.WsStore",
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
        store: "core.basicinfomanage.wsmanage.store.WsStore",
        displayInfo: true,
        flex: 1
    }]

});