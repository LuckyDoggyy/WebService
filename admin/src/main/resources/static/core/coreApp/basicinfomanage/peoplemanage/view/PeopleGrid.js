Ext.define("core.basicinfomanage.peoplemanage.view.PeopleGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.peoplegrid",
    title: "<center height=40>人员信息管理</center>",
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
            fieldLabel: '姓名',
            labelWidth: 30,
            width: 120,
            name: 'name'
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
            text: '增加人员',
            ref: 'addPeople',
            iconCls: 'table_add',
            hidden: false
        }, {
            xtype: 'button',
            text: '修改人员',
            ref: 'updatePeople',
            iconCls: 'modify',
            disabled: true,
            hidden: false
        }, {
            xtype: 'button',
            text: '删除人员',
            ref: 'deletePeople',
            iconCls: 'table_remove',
            disabled: true,
            hidden: false
        }, {
             xtype: 'button',
             text: '查看服务列表',
             ref: 'checkuserservice',
             disabled: true,
             hidden: false
           }]
    }],
    columns: [{
        width: 50,
        height: 36,
        dataIndex: "uid",
        text: '序号',
        align: 'center'
    },  {
        text: "姓名",
        dataIndex: "name",
        width: 140,
        align: 'center'
    }, {
        text: "角色id",
        dataIndex: "rid",
        width: 60,
        align: 'center'
    }, {
        text: "角色",
        dataIndex: "rolename",
        width: 140,
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