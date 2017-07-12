Ext.define("core.basicinfomanage.rolemanage.view.MenuTree", {
    extend: "Ext.tree.Panel",
    alias: "widget.menutree",
    rootVisible: true,
    displayField: "text",
    border: 0,
    store: "core.basicinfomanage.rolemanage.store.MenuTreeStore",
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [{
            xtype: 'textfield',
            fieldLabel: '当前 角色id',
            labelWidth: 88,
            width: 150,
            readOnly: true,
            name: 'rid'
        }, {
            xtype: 'textfield',
            fieldLabel: '角色名',
            labelWidth: 60,
            width: 180,
            readOnly: true,
            name: 'rname'
        }, {
            xtype: 'button',
            text: '保存菜单',
            ref: 'saveMenu',
            iconCls: 'table_save'
        }]
    }]
});