Ext.define("core.basicinfomanage.menumanage.view.UpdateMenuForm", {
			extend : "Ext.form.Panel",
			alias : "widget.updatemenuform",
			mixins : {
				suppleUtil : "core.util.SuppleUtil"
			},
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			items : [{
						xtype : 'textfield',
						fieldLabel : '自增id',
						labelWidth: 80,
						name : 'autoid',
						hidden : true
					},{
						xtype : 'textfield',
						fieldLabel : '菜单编号'+ '<font color=red>*</font>',
						labelWidth: 80,
						name : 'mid',
						allowBlank : false,
						disabled : true,
						blankText : '编号不能为空'
					},{
						xtype : 'textfield',
						fieldLabel : '菜单名'+ '<font color=red>*</font>',
						labelWidth: 80,
						name : 'menuname',
						allowBlank : false,
						blankText : '菜单名不能为空'
					},{
						fieldLabel : '父菜单',
						xtype : "combobox",
						name : 'pid',
						labelWidth : 80,
						emptyText : '请选择菜单',
						queryMode : 'remote',
						store : "core.basicinfomanage.menumanage.store.MenuNodeStore",
						loadingText : '正在加载数据，请稍侯……',
						triggerAction : 'all',
						valueField : 'mid',
						forceSelection : false,
						allowBlank : false,
						displayField : 'menuname'
					},{
						xtype : 'textfield',
						fieldLabel : '页面id',
						labelWidth: 80,
						name : 'viewid',
						allowBlank : true
					},{
						xtype : 'textfield',
						fieldLabel : '页面名',
						labelWidth: 80,
						name : 'viewname',
						allowBlank : true
					},{
						xtype : 'textfield',
						fieldLabel : '页面控制器',
						labelWidth: 80,
						name : 'viewcontroller',
						allowBlank : true
					},{
                          fieldLabel : '流程id',
                          xtype : "combobox",
                          name : 'fid',
                          labelWidth : 80,
                          emptyText : '请选择流程',
                          queryMode : 'remote',
                          store : "core.servicemanage.bpelmanage.store.BPStore",
                          loadingText : '正在加载数据，请稍侯……',
                          triggerAction : 'all',
                          valueField : 'autoid',
                          forceSelection : false,
                          allowBlank : true,
                          displayField : 'flowname'
                      }],
			buttons : [{
						text : '修改',
						ref : 'updateMenu',
						iconCls : "table_save"
					}]
		});