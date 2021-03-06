Ext.define("core.basicinfomanage.rolemanage.view.UpdateRoleGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.updaterolegrid",
    title: "<center height=40>角色更新</center>",
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
            xtype: 'button',
            text: '修改角色',
            ref: 'updateRole'
        }]
    }],
    columns: [{
        text: "角色编号",
        align: 'center',
        dataIndex: "rid",
        width: 80
    }, {
        text: "角色名称",
        align: 'center',
        dataIndex: "rname",
        width: 200
    }],

    store: "core.basicinfomanage.rolemanage.store.RoleStore",
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
        store: "core.basicinfomanage.rolemanage.store.RoleStore",
        displayInfo: true,
        flex: 1
    }]

});