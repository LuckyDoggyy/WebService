Ext.define("core.app.model.TreeModel", { // 定义树节点数据模型
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
    }]
});