Ext.define("core.basicinfomanage.menumanage.view.AddMenu",
				{
					extend : "Ext.form.Panel",
					alias : "widget.addmenu",
					title: "<center height=40>菜单添加</center>",
					bodyPadding : '5 5 0',
					width : 150,
                    height: 300,
					buttonAlign : 'center',
					fieldDefaults : {
						labelAlign : 'left',
						msgTarget : 'side'
					},
					layout : 'anchor',
					items : [{
							xtype : 'textfield',
							fieldLabel : '菜单编号'+ '<font color=red>*</font>',
							anchor: '18%',
							labelWidth: 80,
							name : 'mid',
							allowBlank : false,
							blankText : '编号不能为空'
						},{
							xtype : 'textfield',
							fieldLabel : '菜单名'+ '<font color=red>*</font>',
							anchor: '18%',
							labelWidth: 80,
							name : 'menuname',
							allowBlank : false,
							blankText : '菜单名不能为空'
						},{
							fieldLabel : '父菜单',
							xtype : "combobox",
							name : 'pid',
							anchor: '18%',
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
							anchor: '24%',
							labelWidth: 80,
							name : 'viewid',
							allowBlank : true
						},{
							xtype : 'textfield',
							fieldLabel : '页面名',
							anchor: '24%',
							labelWidth: 80,
							name : 'viewname',
							allowBlank : true
						},{
							xtype : 'textfield',
							fieldLabel : '页面控制器',
							anchor: '24%',
							labelWidth: 80,
							name : 'viewcontroller',
							allowBlank : true
						},{
							 xtype: 'button',
							 text: '添加',
							 ref : 'addMenu',
							 iconCls : "table_save"
						 }]
				});