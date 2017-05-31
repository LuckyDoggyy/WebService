Ext.define("core.basicinfomanage.commonpeoplemanage.view.CommonPeopleGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.commonpeoplegrid",
    title: "<center height=40>注册人员信息</center>",
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
        items: [ {
            xtype: 'textfield',
            fieldLabel: '用户id',
            labelWidth: 48,
            width: 120,
            name: 'uid'
        }, {
            xtype: 'textfield',
            fieldLabel: '用户名',
            labelWidth: 50,
            width: 120,
            name: 'account'
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'search',
            ref: 'searchPeople'
        }]
    }
    ],
    columns: [{
        width: 80,
        height: 36,
        dataIndex: "uid",
        text: 'uid',
        align: 'center'
    },  {
        text: "账号",
        dataIndex: "account",
        width: 200,
        align: 'center'
    },  {
         text: "昵称",
         dataIndex: "nickName",
         width: 140,
         align: 'center'
     }],
    store: "core.basicinfomanage.commonpeoplemanage.store.CommonPeopleStore",
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
        store: "core.basicinfomanage.commonpeoplemanage.store.CommonPeopleStore",
        displayInfo: true,
        flex: 1
    }]

});