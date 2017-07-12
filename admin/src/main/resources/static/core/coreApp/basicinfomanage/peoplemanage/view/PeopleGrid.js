Ext.define("core.basicinfomanage.peoplemanage.view.PeopleGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.peoplegrid",
    title: "<center height=40>人员信息</center>",
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
        /*
         , {
         xtype: 'toolbar',
         dock: 'top',
         items: [{
         xtype: 'button',
         text: '增加人员',
         ref: 'addPeople',
         iconCls: 'table_add',
         hidden: false
         },
         {
         xtype: 'button',
         text: '修改人员',
         ref: 'updatePeople',
         iconCls: 'modify',
         disabled: true,
         hidden: false
         },
         {
         xtype: 'button',
         text: '删除人员',
         ref: 'deletePeople',
         iconCls: 'table_remove',
         disabled: true,
         hidden: false
         }, {
         xtype: 'button',
         text: '查看角色',
         ref: 'checkRole',
         disabled: true,
         hidden: false
         }]
         }*/
    ],
    columns: [{
        width: 80,
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