var Credentials = {
    username: '',
    password: ''
};

var UserData = {};
var UserBlanket = {};
var AllStats;

$(document).ready(function() {

    Initializer.getParams();
    DataLoader.load();
    EventHandler.trackEvents();


});

var EventHandler = {
    trackEvents: function() {

        $('#blanket_submit').click(function() {
            var key = $('#add_serial').val();
            if (key !== '') {
                DataSender.addBlanket(key);
            } else {
                EventHandler.showRemoveBlanketHelp();
            }
        });

        $('#delete_serial').focus(function() {
            EventHandler.hideRemoveBlanketHelp();
        });

        $('#add_serial').focus(function() {
            EventHandler.hideAddBlanketHelp();
        });

        $('#blanket_delete_submit').click(function() {
            var key = $('#delete_serial').val();
            if (key !== '') {
                DataSender.deleteBlanket(key);
            } else {
                EventHandler.showRemoveBlanketHelp();
            }
        });

        $('#statistics').click(function() {
            console.log("#statistics trigger");
            if (!$(this).parent().hasClass('is-active')) {
                console.log("#stats activation");
                EventHandler.hideAllSections();
                EventHandler.deactivateAllTabs();
                $(this).parent().addClass('is-active');
                $('#statisticsPanel').removeClass("is-hidden");
                Renderer.renderStatsPanel();
            }
        });

        $('#status').click(function() {
            console.log("#status trigger");
            if (!$(this).parent().hasClass('is-active')) {
                console.log("#status activation");
                EventHandler.hideAllSections();
                EventHandler.deactivateAllTabs();
                $(this).parent().addClass('is-active');
                $('#statusPanel').removeClass("is-hidden");
                Renderer.renderStatusPanel();
            }
        });

        $('#cabinet').click(function() {
            console.log("#cabinet trigger");
            if (!$(this).parent().hasClass('is-active')) {
                console.log("#cabinet activation");
                EventHandler.hideAllSections();
                EventHandler.deactivateAllTabs();
                $(this).parent().addClass('is-active');
                $('#cabinetPanel').removeClass("is-hidden");
                Renderer.renderCabinet();
            }
        });

        $('#addBlanket').click(function() {
            console.log("#addBlanket trigger");
            if (!$(this).parent().hasClass('is-active')) {
                console.log("#addBlanket activation");
                EventHandler.hideAllSections();
                EventHandler.deactivateAllTabs();
                $(this).parent().addClass('is-active');
                $('#blanketPanel').removeClass("is-hidden");
                Renderer.renderBlanket();
            }
        });
    },

    showRemoveBlanketSuccess: function() {
        $('#remove_blanket_success').removeClass('is-hidden');
        $('#delete_serial').val('');
        setTimeout(function() {
            $('#remove_blanket_success').addClass('is-hidden');
        }, 2500)
    },

    showRemoveBlanketHelp: function() {
        $('#remove_blanket_help').removeClass('is-hidden');
    },

    hideRemoveBlanketHelp: function() {
        $('#remove_blanket_help').addClass('is-hidden');
    },

    showAddBlanketSuccess: function() {
        $('#add_blanket_success').removeClass('is-hidden');
        $('#add_serial').val('');
        setTimeout(function() {
            $('#add_blanket_success').addClass('is-hidden');
        }, 2500)
    },

    showAddBlanketHelp: function() {
        $('#add_blanket_help').removeClass('is-hidden');
    },

    hideAddBlanketHelp: function() {
        $('#add_blanket_help').addClass('is-hidden');
    },

    hideAllSections: function() {
        $('.tab-section').addClass('is-hidden');
    },

    deactivateAllTabs: function() {
        $('#nav_tabs li').removeClass('is-active');
    }

};

var Initializer = {
    getParams: function() {
        Credentials.username = localStorage.getItem("username");//params[0].split('=')[1];
        Credentials.password = localStorage.getItem("password");//params[1].split('=')[1];
    }
};


