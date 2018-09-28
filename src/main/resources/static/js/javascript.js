function reemp(val)
{
    document.getElementById("output1").value = val;
}

function dispOp(op) {
    return document.getElementById(op).onreset;
}



function memory() {
    $.ajax({
        type: 'POST',
        url: '/test3',
        data: 'op1='+dispOp("op1")+'&'+'op2='+dispOp("op2"),
        success: function (data) {
            reemp(data);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });

}

function render_context(clazz) {
    $.ajax({
        type: 'POST',
        url: '/pizza_home',
        data: /*{item:clazz}*/ 'item='+clazz,
        success: function (data) {
            $('#my_item').html(data);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    })
}

function add_to_order(item, clazz) {
	$.ajax({
		type: 'POST',
		url: '/pizza_home/add_to_order',
		data: 'item='+item+'&'+'clazz='+clazz,
		success: function(data) {
            reemp(data)
		},
		error: function(e){
			alert('Error: '+ e);
		}
	});
}

function calcular() {
    $.ajax({
        type: 'POST',
        url: 'CalculatorSpringMVC/calcular',
        data: 'op='+fixOperation(disp()),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            reemp(data);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
}

/*
jQuery(function($) {
    $(window).scroll(function(){
        if($(this).scrollTop()>140){
            $('.navv').addClass('fixed');
        }
        else if ($(this).scrollTop()<140){
            $('.navv').removeClass('fixed');
        }
    });
});*/
