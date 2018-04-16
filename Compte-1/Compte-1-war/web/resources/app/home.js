//Guardar el objeto video en una variable
	    video = document.getElementById("live");
$("#clicker").click(function(){
  //Acceder al dispositivo de camara web para mostrar el video
  navigator.webkitGetUserMedia("video",
      function(stream) {
        video.src = webkitURL.createObjectURL(stream);//Obtenemos el video fuente de nuestra eitqueta video para mostrarlo
      },
      function(err) {
        console.log("Unable to get video stream!");//obtenemos algun error posible al realizar esto
      }
  );
});

$(document).ready(function(){
    $(".cancelmodel").click(function(){
        $("#nn").val('');$("#pn").val('');$("#le").val('');$("#bf").val('');
    });
    $("#Models").hide();
    $("#save").click(function(){
      nn=$("#nn").val();
      pn=$("#pn").val();
      le=$("#le").val();
      date=$("#bf").val();

      $("#dgi_lastname").val(nn);
      $("#dgi_firstname").val(pn);
      $("#dgi_birthplace").val(le);
      $("#dgi_birthday").val(date);
      $(".cancelmodel").click();

    });

    $('#dgi_nin').keypress(function (event) {

       var keyCode = (event.keyCode ? event.keyCode : event.which);
       if(keyCode == 13 || keyCode==9)  // the enter key code
        {
          verifyNifAndNin();
        }
      });

    $('#dgi_nif').keypress(function (event) {
       var keyCode = (event.keyCode ? event.keyCode : event.which);
       if(keyCode == 13 || keyCode==9)  // the enter key code
        {   check();
        }
      });


    //$('#professionel').change(function() {alert($(this).val())});
    $("#department").change(function(){
      var id=$(this).val();
      $.ajax({url:infocommune,type : 'POST',data : 'id='+id})
      .done(function(result){
        $("#Commune").html(result);
      });
    })
    //$('#professionel').change(function() {alert($(this).val())});
    $("#Commune").change(function(){
      var id=$(this).val();
      $.ajax({url:infosection,type : 'POST',data : 'id='+id})
      .done(function(result){
        $("#dgi_idsectioncommunale").html(result);
      });
    })


    verifyNifAndNin=function(){

        var nif=$("#dgi_nif").val();
        var nin=$("#dgi_nin").val();
        $.ajax({
          url:infolinks,
          type : 'POST',
          data :{ nif:nif, nin:nin }
        }).done(function(result){

           var obj = JSON.parse(result);
           if(obj.length==2){
             var NIFCODE=obj[0].nif;
             var NINCODE=obj[1].nin;
             var error='<i style="color:#b70000">information doesn\'t match</i>';
             var valid='<i style="color:green">Match</i>';
             var fname=obj[0].Prenom;
             var fnamenin=obj[1].firstName;
             var fnameerror=error;

             var lname=obj[0].Nom;
             lnamenin=obj[1].lastName;
             lnameerror=error;

             var BPlace=obj[0].lieu_naissance;
             BPlacenin=obj[1].BirthPlace;
             BPlaceerror=error;

             var date=obj[0].date_naissance;
             datenin=obj[1].DOB;
             dateerror=error;

                if(obj[0].Prenom==obj[1].firstName){ fname=obj[0].Prenom;fnamenin=obj[1].firstName;fnameerror=valid;}
                if(obj[0].Nom==obj[1].lastName){ lname=obj[0].Nom;lnamenin=obj[1].lastName;lnameerror=valid;}
                if(obj[0].lieu_naissance==obj[1].BirthPlace){ BPlace=obj[0].lieu_naissance;BPlacenin=obj[1].BirthPlace;BPlaceerror=valid;}
                if(obj[0].date_naissance==obj[1].DOB){ date=obj[0].date_naissance;datenin=obj[1].DOB;dateerror=valid;}
                $("#nn").val(lname);
                $("#pn").val(fname);
                $("#le").val(BPlace);
                $("#bf").val(date);
                //var info='\n<b>'+NIFCODE+'</b>      '+NINCODE+'\n'+fname+'\n'+lname+'\n'+BPlace+'\n'+date;
                //swal(info, "", "success");
                $("#tbody").html('<tr><td>'+NIFCODE+'</td><td>'+NINCODE+'</td><td></td></tr><tr><td>'+fname+'</td><td>'+fnamenin+'</td><td>'+fnameerror+'</td></tr><tr><td>'+lname+'</td><td>'+lnamenin+'</td><td>'+lnameerror+'</td></tr><tr><td>'+BPlace+'</td><td>'+BPlacenin+'</td><td>'+BPlaceerror+'</td></tr><tr><td>'+date+'</td><td>'+datenin+'</td><td>'+dateerror+'</td></tr>');
                $("#Models").click();
           }
          //  var obj = JSON.parse(result);
          // info='\nNIF: '+obj.nif+'\n Nom et prenom: '+obj.Nom+'  '+obj.Prenom+'\n Date de naissance :'+obj.date_naissance+'\n Lieu de naissance:'+obj.lieu_naissance;
          // swal(info, "", "success")06-04-99-1986-10-00021
        });
    }
    check=function(){

        var nif=$("#dgi_nif").val();
        // $.post('http://127.0.0.1/workspace/RMS/home/getNif',{nif: nif}, function(data, status){
        //     alert("Data: " + data + "\nStatus: " + status);
        // });
        $.ajax({
          url:infolink,
          type : 'POST',
          data :{nif:nif}
        }).done(function(result){
           var obj = JSON.parse(result);

          info='\nNIF: '+obj.nif+'\n Nom et prenom: '+obj.Nom+'  '+obj.Prenom+'\n Date de naissance :'+obj.date_naissance+'\n Lieu de naissance:'+obj.lieu_naissance;
          swal(info, "", "success")
        });
    }
    $(":input").inputmask();
    $(".select2_multiple").select2({
      maximumSelectionLength: 5,
      placeholder: "With Max Selection limit 2",
      allowClear: true
    });

    // Smart Wizard
    $('#wizard').smartWizard({
      labelNext:'Suivant', // label for Next button
      labelPrevious:'Precedent', // label for Previous button
      labelFinish:'Poster',//,  // label for Finish button onLeaveStep:leaveAStepCallback,
      onFinish:onFinishCallback,
      enableFinishButton:false
    });


    function leaveAStepCallback(obj, context){
      var step_num= obj.attr('rel');
      return validateSteps(step_num);
    }

  function onFinishCallback(objs, context){
    //if(validateAllSteps()){
        $('form').submit();
    //}
  }


    function validateSteps(stepnumber){
        var isStepValid = true;
        // validate step 1
        if(stepnumber >= 1){
          if(validateStep1() == false ){
            isStepValid = false;
          }
        }
        return isStepValid;
        // ...
    }

    function validateAllSteps(){
      var isStepValid = true;
      // all step validation logic
      if(validateStep1() == false ){isStepValid = false;}
      return isStepValid;
    }

    function validateStep1(){
       var isValid = true;
       // Validate nom
       $('#msg_dgi_nif').html('').hide();
       $('#msg_dgi_nin').html('').hide();
       $('#msg_dgi_nationality').html('').hide();
       $('#msg_dgi_firstname').html('').hide();
       $('#msg_dgi_lastname').html('').hide();
       $('#msg_dgi_birthday').html('').hide();

       var nif = $('#dgi_nif').val();
       var nin = $('#dgi_nin').val();
       var nationality = $('#dgi_nationality').val();
       var lastname = $('#dgi_lastname').val();
       var firstname = $('#dgi_irstname').val();
       var birthday = $('#dgi_birthday').val();

       if(!nif && nif.length <= 0){isValid = false;$('#msg_dgi_nif').html('S\'il vous plaît veuillez remplir le champ NIF').show();}
       else if(!nin && nin.length <= 0){isValid = false;$('#msg_dgi_nin').html('S\'il vous plaît veuillez remplir le champ NIN').show();}
       else if(!dgi_nationality && dgi_nationality.length <= 0){isValid = false;$('#msg_dgi_nationality').html('S\'il vous plaît veuillez selectionner une nationalite').show();}
       else if(!lastname && lastname.length <= 0){isValid = false;$('#msg_dgi_lastname').html('S\'il vous plaît veuillez remplir le champ Nom').show();}
       else if(!firstname && firstname.length <= 0){isValid = false;$('#msg_dgi_firstname').html('S\'il vous plaît veuillez remplir le champ Prenom').show();}
       else if(!birthday && birthday.length <= 0){isValid = false;$('#msg_dgi_birthday').html('S\'il vous plaît veuillez remplir le champ Date de naissance').show();}
      return isValid;
    }

   });
