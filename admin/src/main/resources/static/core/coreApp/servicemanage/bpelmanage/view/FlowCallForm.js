Ext.define("core.servicemanage.bpelmanage.view.FlowCallForm", {
			extend : "Ext.form.Panel",
			alias : "widget.flowcallform",
			mixins : {
				suppleUtil : "core.util.SuppleUtil"
			},
			layout : 'anchor',
			align : 'center',
			buttonAlign : 'center',
			items : [{
						xtype : 'textfield',
						fieldLabel : 'id',
						name : 'autoid',
						hidden : true
					},{
						xtype : 'textfield',
						fieldLabel : '业务标识',
						labelWidth: 70,
						width: 350,
						name : 'flowid',
						allowBlank : true
					},{
						xtype : 'textfield',
						fieldLabel : '业务名称',
						labelWidth: 70,
						width: 350,
						name : 'flowname',
						allowBlank : true
					},{
						xtype : 'textfield',
						fieldLabel : '详情',
						labelWidth: 70,
						width: 350,
						name : 'description',
						allowBlank : true
					},{
                        xtype : 'textfield',
                        fieldLabel : '输入',
                        name : 'input',
                        hidden : true
                    },{
                        xtype : 'button',
                        text : '调用',
                        ref : 'call',
                        width: 60
                    }]
		});