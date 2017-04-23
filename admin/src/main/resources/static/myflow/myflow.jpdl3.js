(function($){
var myflow = $.myflow;
//		各节点的name，text，typw，props
$.extend(true,myflow.config.props.props,{		//	将name、key、desc合并到myflow.config.props.props中
	name : {name:'name', label:'名称', value:'新建流程', editor:function(){return new myflow.editors.inputEditor();}},
	key : {name:'key', label:'标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputEditor();}}
});

$.extend(true,myflow.config.tools.states,{		//将start、end……合并到myflow.config.tools.states中
	start : {
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'开始'},
				img : {src : 'img/16/startnode.gif',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
    end : {
		type : 'end',
        name : {text:'<<end>>'},
        text : {text:'结束'},
        img : {src : 'img/16/endnode.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '名称', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'},
            temp1: {name:'temp1', label : '输入', value:'', editor: function(){return new myflow.editors.inputEditor();}},
            temp2: {name:'temp2', label : '输出', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
        }},
	empty : {
		type : 'empty',
        name : {text:'<<empty>>'},
        text : {text:'空操作'},
        img : {src : 'img/16/empty.gif',width : 16, height:16},
        props : {

        }},
    invoke : {
		type : 'invoke',
        name : {text:'<<invoke>>'},
        text : {text:'结束'},
        img : {src : 'img/16/invoke.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '名称', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'调用'},
            temp1: {name:'temp1', label : '输入', value:'', editor: function(){return new myflow.editors.inputEditor();}},
            temp2: {name:'temp2', label : '输出', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
        }},
    receive : {
        type : 'invoke',
        name : {text:'<<receive>>'},
        text : {text:'接收'},
        img : {src : 'img/16/receive.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'接收'},
            temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
            temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
        }},
    reply : {
        type : 'invoke',
        name : {text:'<<reply>>'},
        text : {text:'返回'},
        img : {src : 'img/16/reply.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'返回'},
            temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
            temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
        }},
    if : {
        type : 'if',
        name : {text:'<<if>>'},
        text : {text:'判断'},
        img : {src : 'img/16/if.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'if'},
            temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
            temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
        }},
			'end-cancel' : {type : 'end-cancel',
				name : {text:'<<end-cancel>>'},
				text : {text:'取消'},
				img : {src : 'img/16/end_event_cancel.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'取消'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			'end-error' : {type : 'end-error',
				name : {text:'<<end-error>>'},
				text : {text:'错误'},
				img : {src : 'img/16/end_event_error.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'错误'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			state : {type : 'state',
				name : {text:'<<state>>'},
				text : {text:'状态'},
				img : {src : 'img/16/task_empty.png',width : 16, height:16},
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'状态'},
					temp1: {name:'temp1', label : '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			fork : {type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'分支'},
				img : {src : 'img/16/gateway_parallel.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'分支'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}
				}},
			join : {type : 'join',
				name : {text:'<<join>>'},
				text : {text:'合并'},
				img : {src : 'img/16/gateway_parallel.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'合并'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}
				}},
			task : {type : 'task',
				name : {text:'<<task>>'},
				text : {text:'任务'},
				img : {src : 'img/16/task_empty.png',width :16, height:16},
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'任务'},
					temp1: {name:'temp1', label: '文本', value:'', editor: function(){return new myflow.editors.inputEditor();}},
					temp2: {name:'temp2', label : '选择', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}}
				}}
});
})(jQuery);