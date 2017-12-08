hrefRedirect = "https://localhost:8443/ui/index.html";

$(document).ready(function() {


    $('#registration').submit(function(event) {
        event.preventDefault();
        Registration.addUser(this);
        console.log("[INFO] RegistrationForm SUBMIT");
    });

    $('#registration #username input')
        .blur(function() {
            console.log("Username blur");
            Registration.checkUsername();})
        .focus(function() {
            $(this).removeClass('is-danger is-success');
            $('#usernameP').css("display", 'none');
        });

    $('#authorization').submit(function() {
        event.preventDefault();
        Authorization.authorize(this);
    });

});

var Authorization = {
    authorize: function(form) {
        console.log("Authorization");
        var authData = Registration.serializeForm(form);
        console.log("Credentials: " + authData);
        $.ajax({
            url: '/rest/user/authorize',
            method: 'POST',
            contentType: 'application/json',
            data: authData,
            success: function(response) {
                if (response === 'Denied') {
                    console.log("Incorrect login/password");
                    $('#authError').css("display", "block");
                } else {
                    console.log("Correct login/password");
                    $('#authError').css("display", "none");
                    $('#authorization')[0].reset();
                    $('#authorizationPanel').modal('hide');
                    console.log("Login: " + response.split('\n')[0]);
                    console.log("Password: " + response.split('\n')[1]);
                    localStorage.setItem('username', response.split('\n')[0]);
                    localStorage.setItem('password', response.split('\n')[1]);
                    location.href = hrefRedirect;
                    //+ "?username=" + response.split('\n')[0] + "&" + "password=" + response.split('\n')[1];
                }
                console.log("[INFO] Authorization response: " + response);
            }
        });
    }
};

var Registration = {
    formElement: '#registration',
    usernameInputElement: '#usernameInput',
    visible: false,
    usernameVal: '',

    _updateUsername: function() {
        console.log("[INFO] Username filed update to val: " + $(this.usernameInputElement).val());
        this.usernameVal = $(this.usernameInputElement).val();
    },

    _checkVisible: function() {
        this.visible = $('#registrationPanel').css('display') === 'block';
    },

    addUser: function(form) {
        var formData = this.serializeForm(form);
        console.log('Form data: ' + formData);
        $.ajax({
            url: '/rest/user/addUser',
            method: 'POST',
            contentType: 'application/json',
            data: formData,
            success: function(response) {
                if (+response > 0) {
                    $('#registration')[0].reset();
                    $('#usernameInput').removeClass('is-danger is-success');
                    $("#regError").css("display", "none");
                    $("#regSuccess").css("display", "block");
                    setTimeout( function(){
                        $('#registrationPanel').modal('hide');
                    }  , 2000 );

                } else {
                    $("#regError").css("display", "block");
                    $("#regSuccess").css("display", "none");
                }
                console.log("[INFO] User add response: " + response);
            }
        });
    },

    checkUsername: function() {
        // if ($(this.usernameInputElement).val() !== this.usernameVal) {
            this._updateUsername();
            console.log("[INFO] Username check ajax request");
            console.log("[INFO] Username for check = " + this.usernameVal);
            event.preventDefault();
            $.ajax({
                url: '/rest/user/checkUsername/' + this.usernameVal,
                method: 'GET',
                success: function(response) {
                    console.log("[INFO] Username check response: " + response);
                    console.log(":"+response+":" + "   " + (response == true));
                    if (new String(response)[0] == 't') {
                        console.log("Username is busy!");
                        $('#usernameP').css("display", 'block');
                        $('#usernameInput').addClass("is-danger");
                    } else {
                        console.log("Username is free!");
                        $('#usernameP').css("display", 'none');
                        $('#usernameInput').addClass("is-success");
                    }
                }
            });
        // }
    },

    serializeForm: function(form) {
        var obj = {};
        var elements = form.querySelectorAll("input, select");
        for(var i = 0; i < elements.length; ++i) {
            var element = elements[i];
            var name = element.name;
            var value = element.value;

            if(name) {
                obj[name] = value;
            }
        }
        delete obj['password1'];
        return JSON.stringify(obj);
    }
};

