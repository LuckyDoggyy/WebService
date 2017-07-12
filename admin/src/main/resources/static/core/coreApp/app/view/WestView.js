Ext.define("core.app.view.WestView", {
    extend: 'Ext.panel.Panel',
    alias: 'widget.westview',
    id: "westview",
    collapsible: true,
    split: true,
    border: 1,
    margins: '2 2 0 0',
    width: 200,
    minSize: 100,
    maxSize: 250,
    title: "系统菜单",
    layout: 'accordion',
    layoutConfig: {
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },

//	items : [{
//        		title : "信息管理",
//        		items : [ {
//        			xtype : "treepanel",
//        			id : 'basicinfomanage',
//        			rootVisible : false,// 不展示根节点
//        			displayField : "text",
//        			border : 0,
//        			store : Ext.create("Ext.data.TreeStore", {
//        				defaultRootId : 'basicinfomanage', // 默认的根节点id
//        				model : "core.app.model.TreeModel",
//        				proxy : {
//        					type : "ajax", // 获取方式
//        					url : "getMenu" // 获取树节点的地址
//        				},
//        				clearOnLoad : false,
//        				nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
//        			})
//        		}]
//        	}],

    initComponent: function () {
        this.callParent(arguments);
        var view = this;
        view.removeAll()

        Ext.Ajax.request({
            url: "getParentMenu",
            method: "GET",
            timeout: 4000,
            sync: false,
            success: function (response, opts) {
                var resObj = Ext.decode(response.responseText);
                for (var i = 0; i < resObj.length; i++) {
                    var mid = resObj[i].mid;
                    var name = resObj[i].menuname;
                    var item = {
                        title: name,
                        items: [{
                            xtype: "treepanel",
                            id: mid,
                            rootVisible: false,// 不展示根节点
                            displayField: "text",
                            border: 0,
                            store: Ext.create("Ext.data.TreeStore", {
                                defaultRootId: mid,
                                model: "core.app.model.TreeModel",
                                proxy: {
                                    type: "ajax", // 获取方式
                                    url: "getMenu" // 获取树节点的地址
                                },
                                clearOnLoad: false,
                                nodeParam: "node"// 设置传递给后台的参数名,值是树节点的id属性
                            })
                        }]
                    };
                    view.add(item);
                }
                view.doLayout();
            }
        });
    }
});
