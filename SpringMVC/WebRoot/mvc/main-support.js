/************************************** Store 部分  *****************************************/
/** 用户管理 Store */
Ext.define('KitchenSink.store.UserManagerStore', {
    extend: 'Ext.data.TreeStore',
    root: {
        expanded: true,
        children: [{ 
        	leaf:true, 
        	text: '用户管理',
        	view: 'user?user-main.html'
        }]
    }
});
/** 菜单管理 Store */
Ext.define('KitchenSink.store.MenuManagerStore', {
    extend: 'Ext.data.TreeStore',
    root: {
        expanded: true,
        children: [{ 
        	leaf:true, 
        	text: '主页菜单管理',
        	view: ''
        },{ 
        	leaf:true, 
        	text: '权限菜单管理',
        	view: ''
        },{ 
        	leaf:true, 
        	text: '功能菜单管理',
        	view: ''
        }]
    },
    listeners: {
    	'itemclick' : function(view, record, item,index,e){
    		var view = record.get('view')
    		Ext.getCmp('contentWrapper').loadUrl(root + view);
    	}
    }
});

/*****************************************  Tree 部分   *********************************************/
/** 用户管理 Trees */
Ext.define('KitchenSink.view.tree.UserManagerTrees', {
    extend: 'Ext.Container',
    xtype: 'basic-trees',

    defaults: {
        xtype: 'treepanel',
        rootVisible: false,
        store: Ext.create('KitchenSink.store.UserManagerStore'),
        listeners: {
	    	click: {
	    		element: 'el',
	    		fn: function(obj, text, item){
	    			var cmp = Ext.getCmp('contentWrapper');//.loadUrl(root + obj.record.get('view'));
	    			cmp.config.title = obj.record.get('text');
	    			cmp.loader = ({url: root + obj.record.get('view'),autoLoad: true});
	    			console.log(cmp)
	    			cmp.reload();
	    		}
	    	}
	    }
    },
    
    initComponent: function() {
        this.items = [{}];
        this.callParent();
    }
});
/** 菜单管理 Trees */
Ext.define('KitchenSink.view.tree.MenuManagerTrees', {
    extend: 'Ext.Container',
    xtype: 'basic-trees',

    defaults: {
        xtype: 'treepanel',
        rootVisible: false,
        store: Ext.create('KitchenSink.store.MenuManagerStore')
    },
    
    initComponent: function() {
        this.items = [{}];
        this.callParent();
    }
});

/********************************************  Accordion 部分   ***********************************************/

Ext.define('MVC.view.layout.Accordion', {
    extend: 'Ext.panel.Panel',
    requires: [
        'Ext.layout.container.Accordion'
    ],
    defaults: {
    	layout: 'fit',
    },
    xtype: 'layout-accordion',
    layout: 'accordion',
    initComponent: function() {
        Ext.apply(this, {
            items: [{
                title: '<i class="icon-user"></i> 用户管理',
                items: Ext.create('KitchenSink.view.tree.UserManagerTrees')
            }, {
                title: '<i class="icon-reorder"></i> 菜单管理',
                items: Ext.create('KitchenSink.view.tree.MenuManagerTrees')
            }, {
                title: '<i class="icon-wrench"></i> 系统设置',
                items: Ext.create('KitchenSink.view.tree.UserManagerTrees')
            }, {
                title: 'Accordion Item 4',
                items: Ext.create('KitchenSink.view.tree.UserManagerTrees')
            }, {
                title: 'Accordion Item 5',
                items: Ext.create('KitchenSink.view.tree.UserManagerTrees')
            }]
        });
        this.callParent();
    }
});

