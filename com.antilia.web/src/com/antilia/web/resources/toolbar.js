
function Toolbar(id, children) {
	this.id = id;	
	this.children = children;	
}

Toolbar.prototype.hideOther = function() { 
    if(this.children.size != 0) {
        for(children) {
            
        }
    }    
}

Table.prototype.removeDroppables = function() { 
    for(var j = 1; j < this.ncols; j++) {               
        //var titleId = this.id + '_title_' + j;              
        var titleId = this.id + '_dragger_' + j;
        try { Droppables.remove(document.getElementById(titleId)) } catch (err) {};
        
    }
}

Table.prototype.addColumn = function(col) {	
	var column = new Column(this.id, col);	
	this.columns[this.columns.length]= column;
}

Table.prototype.toggleSelected = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.toggleSelection();
}

Table.prototype.highlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.highlight();
}

Table.prototype.unhighlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.unhighlight();
}

function Column(tableId, number) {
	this.tableId = tableId;
	this.number = number;
	this.resizeHandleId = this.tableId + '_c_' + this.number;
	this.resizeHandle = document.getElementById(this.resizeHandleId);	
	if(this.resizeHandle)
		Antilia.Drag.init(this.resizeHandle, function() {} , function() { }, this.onResize);		
}


Column.prototype.onResize = function (obj, deltaX, deltaY) {
	var td = obj.parentNode.parentNode.parentNode.parentNode;		
	if ((parseInt(td.offsetWidth)+(deltaX))>0) {
    	td.style.width =  parseInt(td.offsetWidth)+deltaX+"px";
		td.getElementsByTagName("input")[0].value=parseInt(td.style.width);
    }
    else
  		td.style.width=1;
  	var res = [0, 0];
	return res;
}	
 
function Row(tableId, number, selected) {
	this.tableId = tableId;
	this.number = number;
	this.selected = selected;
	this.rowId = this.tableId + '_r_' + this.number;
	this.row = document.getElementById(this.rowId);		
}

Row.prototype.toggleSelection = function () {
	if(this.selected==false) {
		this.selected = true;	
	} else {
		this.selected = false;
	}		
}

Row.prototype.highlight = function () {	
	if(this.row) {
		this.row.className = 'highlightedRow';
	}	
}

Row.prototype.unhighlight = function () {
	var styleClass = 'tbodyrow' + (this.number%2);
	if(this.row) {
		this.row.className = styleClass;
	}	
}