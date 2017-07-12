Ext.define("core.servicemanage.tagmanage.view.DeleteTagGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.deletetaggrid",
    title: "<center height=40>分类删除</center>",
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
        height: 36,
        items: [{
            xtype: 'button',
            text: '删除分类',
            ref: 'deleteTag',
            iconCls: 'table_remove',
            disabled: true
        }]
    }],
    columns: [{
        text: "分类编号",
        dataIndex: "autoId",
        align: 'center',
        width: 150
    }, {
        text: "分类名",
        dataIndex: "tagName",
        align: 'center',
        width: 120
    }],
    store: "core.servicemanage.tagmanage.store.TagStore",
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
        store: "core.servicemanage.tagmanage.store.TagStore",
        displayInfo: true,
        flex: 1
    }]

});