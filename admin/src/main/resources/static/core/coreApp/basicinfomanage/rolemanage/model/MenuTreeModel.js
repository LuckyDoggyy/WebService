Ext.define("core.basicinfomanage.rolemanage.model.MenuTreeModel", { // 定义树节点数据模型
    extend: "Ext.data.Model",
    fields: [{
        name: "id",
        type: "string"
    }, {
        name: "text",
        type: "string"
    }, {
        name: "leaf",
        type: "boolean"
    }, {
        name: "expanded",
        type: "boolean"
    }, {
        name: "checked",
        type: "boolean"
    }]

});