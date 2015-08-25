
var grid = Ext.create('Ext.grid.Panel', {
	id: 'mvc-user-grid',
    title: '用户管理',
    store: store,
    selModel: sm,
    columns: columns.userColumns,
    columnLines: true,
    viewConfig : {
		forceFit : true
	},
    tbar: [{
    	text : '添加',  
        iconCls:'icon-plus',
        handler: function() {
        	form.reset();
        	form.url = root + "user?method=saveUser";
        	formWin.show();
        }
    },{
    	text : '删除',  
        iconCls:'icon-remove',
        handler: function() {
        	var records = grid.getSelection( );
        	if(records.length>0){
        		var ids = new Array();
        		Ext.each(records,function(n,i){
        			ids.push(n.data.suuid);
        		});
        		Ext.Ajax.request({
				    url: root + 'user?method=removeUser',
				    params: {
				        'ILC': ids
				    },
				    success: function(response){
				        var text = Ext.util.JSON.decode(response.responseText);;
				        Ext.Msg.alert('提示', text.message);   
				        store.reload();
				    },
                    failure: function(response) {
                    	var text = Ext.util.JSON.decode(response.responseText);;
                        Ext.Msg.alert('错误', text == null ? text.message : '连接失败');
                    }
				});
        	}else{
        		Ext.Msg.alert('提示', '请选择一条记录');        
        	}
        }
    },{
    	text : '修改',  
        iconCls:'icon-edit',
        handler: function() {
        	var records = grid.getSelection();
        	if(records.length>0){
	        	form.reset();
	        	form.url = root + "user?method=updateUser";
	        	form.loadRecord(records[0]);
	        	formWin.show();
        	}else{
				Ext.Msg.alert('提示', '请选择一条记录');        	
        	}
        }
    },{
    	text : '查看',  
        iconCls:'icon-search',
        handler: function() {
        	var records = grid.getSelection();
        	if(records.length>0){
        		console.log(records)
        	}else{
        		Ext.Msg.alert('提示', '请选择一条记录');        
        	}
        }
    },"-",{
    	xtype : 'combo',
		//fieldLabel : '搜索',
		hiddenName : 'searchType',
		store : [['userName',"用户名"],['userCode',"用户编号"]],
		value : 'userName',
		mode : 'local',
		width: 100,
		id :'mvc-user-earchType',
		emptyText : '请选择',
		triggerAction: 'all',
		forceSelection : true,
		readOnly : false,
		editable : true
    },{
		xtype: 'textfield',
		name: 'searchText',
		hideLabel: true,
		width: 200
    },{
    	text : '搜索',  
        iconCls:'icon-search',
        handler: function() {
        	alert("查看方法")
        }
    }],
    bbar:{ 
    	xtype: "pagingtoolbar", 
    	store: store, 
    	pageSize : 15,
    	displayInfo: true 
    },
    listeners: {
    	render : function(gd) {
    		gd.store.load({
				params : {
					'page.start' : 0,
					'page.limit' : 20
				}
			});
    	}
    }
});


Ext.onReady(function(){   

	//开启悬浮提示功能
    Ext.QuickTips.init();
    //开启动态加载
    Ext.Loader.setConfig({
        enabled: true
    }); 

    var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		defaults : {
			border : false
		},
		items : grid
	});
	
});