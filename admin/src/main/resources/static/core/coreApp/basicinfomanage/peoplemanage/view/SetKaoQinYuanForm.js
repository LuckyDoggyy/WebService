Ext.define('core.basicinfo.peoplemanage.view.SetKaoQinYuanForm', {
			extend : "Ext.form.Panel",
			alias : "widget.setkaoqinyuanform",
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			buttons : [{
						xtype : "button",
						ref : "addkaoqinyuan",
						iconCls : "table_save",
						text : "添加"
					}, {
						xtype : "button",
						ref : "return",
						iconCls : "return",
						text : "返回"

					}],
			items : [{
						xtype : "textfield",
						fieldLabel : "工号" + '<font color=red>*</font>',
						labelWidth : 65,
						padding : '5,20,5,30',
						name : "usergh",
						allowBlank : false,// 不允许为空
						blankText : '工号不能为空',// 错误提示内容
						emptyText : '请输入新考勤员的工号',
						readOnly : false
					}, {
						fieldLabel : '所在部门' + '<font color=red>*</font>',
						xtype : "combobox",
						name : 'deptbh',
						padding : '5,20,5,30',
						labelWidth : 65,
						emptyText : '请选择部门',
						queryMode : 'remote', // 这个需要加上。。
						store : "core.kaoqinmanager.peoplemanage.store.DeptStore",
						loadingText : '正在加载数据，请稍侯……',
						triggerAction : 'all',
						valueField : 'deptbh',
						forceSelection : false,
						allowBlank : false,
						displayField : 'deptname'
						
						
					}, {

						fieldLabel : '考勤班组' + '<font color=red>*</font>',
						xtype : "combobox",
						name : 'banzubh',
						id : 'deptbanzucombo',
						padding : '5,20,5,30',
						labelWidth : 65,
						emptyText : '请选择考勤班组',
						queryMode : 'local', // 这个需要加上。。
						store : "core.kaoqinmanager.peoplemanage.store.BanZuStore",
						loadingText : '正在加载数据，请稍侯……',
						triggerAction : 'all',
						valueField : 'banzubh',
						forceSelection : true,
						allowBlank : false,
						displayField : 'banzuname'

					}],
			initComponent : function() {
				this.callParent(arguments);
			}

		});