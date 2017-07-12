Ext.define("core.basicinfomanage.rolemanage.view.SetRoleMenuGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.setrolemenugrid",
    title: "<center height=40>角色菜单管理</center>",
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
            {
                xtype: 'button',
                text: '设置角色权限',
                ref: 'setroleright',
                iconCls: 'roleright',
                disabled: true
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