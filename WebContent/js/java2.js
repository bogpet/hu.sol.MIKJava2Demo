(function () {
    var url = "http://localhost:8888";

    function isValidEmail(email) {
        if (isEmpty(email)) {
            return false;
        }
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function isEmpty(obj) {
        if (obj == null) return true;

        if (obj.length > 0) return false;

        if (obj.length === 0) return true;

        if (typeof obj !== "object") return true;

        for (var key in obj) {
            if (Object.prototype.hasOwnProperty.call(obj, key)) return false;
        }

        return true;
    }

    function signUp() {
        var registered = localStorage.getItem('java2-registered');
        if (registered == null || jQuery.isEmptyObject(registered) || registered === 'false') {
            var valid = true;
            var name = $('#name-input').val();
            var email = $('#email-input').val();
            var subjectId = $('#subject-select').find(":selected").val();
            if (jQuery.isEmptyObject(name)) {
                $('#name-div').addClass('has-error has-feedback');
                $('#name-span').addClass('glyphicon glyphicon-remove form-control-feedback');
                valid = false;
            } else {
                $('#name-div').removeClass('has-error has-feedback');
                $('#name-span').removeClass('glyphicon glyphicon-remove form-control-feedback');
            }
            if (!isValidEmail(email)) {
                $('#email-div').addClass('has-error has-feedback');
                $('#email-span').addClass('glyphicon glyphicon-remove form-control-feedback');
                valid = false;
            } else {
                $('#email-div').removeClass('has-error has-feedback');
                $('#email-span').removeClass('glyphicon glyphicon-remove form-control-feedback');
            }
            if (subjectId === "") {
                $('#subject-div').addClass('has-error has-feedback');
                $('#subject-span').addClass('glyphicon glyphicon-remove form-control-feedback');
                valid = false;
            } else {
                $('#subject-div').removeClass('has-error has-feedback');
                $('#subject-span').removeClass('glyphicon glyphicon-remove form-control-feedback');
            }
            if (valid) {
                $.ajax(url + '/sign', {
                    type: 'POST',
                    data: {
                        name: name,
                        email: email,
                        subjectId: subjectId
                    },
                    success: function (obj) {
                        $('#form-div').css('display', 'none');
                        $('#success-div').css('display', 'inline');
                        localStorage.setItem('java2-registered', 'true')
                    },
                    fail: function (obj) {
                        $('#form-div').css('display', 'none');
                        $('#fail-div').css('display', 'inline');
                    },
                    crossDomain: true
                });
            }
        }
    }

    $(function () {
        var registered = localStorage.getItem('java2-registered');
        if (registered === 'true') {
            $('#form-div').css('display', 'none')
        }
        $('.dropdown-toggle').dropdown();
        $.getJSON(url + '/subjects', function (result) {
            $.each(result, function (i, obj) {
                $('#subject-select').append(`<option value='${obj.subjectId}'>${obj.subjectName}</option>`);
            });
        });

        $('#signUp').on('click', signUp);
    });
})();