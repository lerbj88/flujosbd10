$('#select-all').click(function(event) {   
    if(this.checked) {

        $(':checkbox').each(function() {
            this.checked = true;                        
        });
    } else {
        $(':checkbox').each(function() {
            this.checked = false;                       
        });
    }
});


function retornarFecha()
{
    var fecha
    fecha=new Date();
    var cadena=fecha.getDate()+'/'+(fecha.getMonth()+1)+'/'+fecha.getYear();
    return cadena;
}


    $(document).ready(function() {
        changePageAndSize();

        $(document).on("click", ".show-alert", function(e) {
            bootbox.alert("Hello world!", function() {
                console.log("Alert Callback");
            });
        });


        $(document).on("click", "#delUser", function(e) {
            bootbox.confirm("confirma!", function() {

            });
        });

    });

function changePageAndSize() {
    $('#pageSizeSelect').change(function(evt) {
        window.location.replace("/?size=" + this.value + "&page=1");
    });
}



$(function() {

    $('#tablaerrores tr').each(function () {
        $(this).find('.intentos').each(function () {
            if ($(this).html() >=5){
                $(this).css("background-color", "#FFC186")
                //$('.filas').find(":checkbox").prop("disabled", true);
            }

    })
    })

});


$(function () {
    $('#destinoiseries').hide();
    $('#destinooracle').hide();
    $('#user400').hide();
    $('#pwd400').hide();
    $('#valuser').hide();
    $('#fiorigen').on("change", function() {
        console.log($('#fiorigen option:selected').val());
        if($('#fiorigen').val() == '1') {
            $('#destinoiseries').show();
            $('#destinooracle').hide();
            $('#user400').show();
            $('#pwd400').show();
            $('#valuser').show();
        } else {
            $('#destinooracle').show();
            $('#destinoiseries').hide();
            $('#user400').hide();
            $('#pwd400').hide();
            $('#valuser').hide();
        }


    });
});


$(function() {
    setTimeout(function () {
        $("#valuser400").fadeOut(1500);
    }, 3000);
});

