Ext.define("core.basicinfomanage.rolemanage.store.MenuTreeStore", {
    extend: 'Ext.data.TreeStore',
    model: 'core.basicinfomanage.rolemanage.model.MenuTreeModel',
    id: 'treestore',
    proxy: {
        type: 'ajax',
        url: 'role/listRoleMenu',
        reader: {
            type: 'json'
        }
    },
    root: {
        id: 'root',
        name: '所有菜单',
        expanded: false
    },
    autoLoad: false
});
