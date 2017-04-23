(function($){
var myflow = $.myflow;

//从后端获取一些下拉选项
var services;
$.ajax({
        url:'../ws/getWsOption',
        type:'POST',
        async:false,
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log(data);
            services=data;
        },
        error:function(xhr,textStatus){
            //alert("请求失败");
        }
        })


//		各节点的name，text，typw，props 	将name、key、desc合并到myflow.config.props.props中
$.extend(true,myflow.config.props.props,{
	name : {name:'name', label:'名称', value:'新建流程', editor:function(){return new myflow.editors.inputEditor();}},
	flowid : {name:'flowid', label:'标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}}
});

$.extend(true,myflow.config.tools.states,{		//将start、end……合并到myflow.config.tools.states中
	start : {
		type : 'start',
		name : {text:'<<start>>'},
		text : {text:'开始'},
		img : {src : 'img/16/startnode.gif',width : 16, height:16},
		props : {
			text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
		}},
    end : {
		type : 'end',
        name : {text:'<<end>>'},
        text : {text:'结束'},
        img : {src : 'img/16/endnode.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '名称', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'}
        }},
    invoke : {
		type : 'invoke',
        name : {text:'<<invoke>>'},
        text : {text:'调用'},
        img : {src : 'img/16/invoke.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '名称', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'调用'},
            desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}},
            input: {name:'input', label : '输入', editor: function(){return new myflow.editors.tableEditor();}},
            output: {name:'output', label : '输出', value:'', editor: function(){return new myflow.editors.tableEditor();}}
        }},
    receive : {
        type : 'invoke',
        name : {text:'<<receive>>'},
        text : {text:'接收'},
        img : {src : 'img/16/receive.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'接收'},
            desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}}
        }},
    reply : {
        type : 'invoke',
        name : {text:'<<reply>>'},
        text : {text:'返回'},
        img : {src : 'img/16/reply.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'返回'},
            desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}}
        }},
    IF : {
        type : 'if',
        name : {text:'<<if>>'},
        text : {text:'判断'},
        img : {src : 'img/16/if.gif',width : 16, height:16},
        props : {
            text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'if'},
            desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}},
            judge: {name:'judge', label: '判断', value:'', editor: function(){return new myflow.editors.inputEditor();}}
        }},
	empty : {
		type : 'empty',
		name : {text:'<<empty>>'},
		text : {text:'空操作'},
		img : {src : 'img/16/empty.gif',width : 16, height:16},
		props : {
			desc : {name:'desc', label:'描述', value:'', editor:function(){return new myflow.editors.inputAreaEditor();}}
		}},
	task : {type : 'task',
		name : {text:'<<task>>'},
		text : {text:'任务'},
		img : {src : 'img/16/task_empty.png',width :16, height:16},
		props : {
			text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'任务'},
			taskremark: {name:'taskremark', label: '任务注释', value:'', editor: function(){return new myflow.editors.inputAreaEditor();}},
            taskcategory: {name:'taskcategory', label : '任务类别', value:'', editor: function(){return new myflow.editors.selectEditor(services);}}
		}}
});
})(jQuery);