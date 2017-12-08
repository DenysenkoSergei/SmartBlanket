var Util = {
    getCssNumberValue: function(value) {
        return value.split('p')[0];
    }
};

var Renderer = {

    renderGeneralUserData: function(user) {
        console.log(UserData.blanketsById[0]);
        // UserData.email;
        // UserData.phoneNbr;
    },

    renderStatsPanel: function() {
        var canvasWidth = 500;
        var canvasHeight = 400;

        var avgTemperatureArr = [];
        var avgVibrationArr = [];
        var labels = [];
        for (var i = 0; i < AllStats.length; ++i) {
            labels.push(new Date(AllStats[i].date).toLocaleDateString("en-US"));
            avgTemperatureArr.push(AllStats[i].averageTemp);
            avgVibrationArr.push(AllStats[i].averageVibr);
        }
        var allDays = Math.floor((AllStats[AllStats.length - 1].date - AllStats[0].date)/(60 * 60 * 1000 * 24));
        var lastSync = new Date(AllStats[AllStats.length - 1].date).toLocaleDateString("en-US");

        $('#all_days').html(allDays + " days");
        $('#last_sync').html(lastSync);

        var canvas1 = document.getElementById("chart_temp_avg");
        var canvas2 = document.getElementById("chart_vibr_avg");
        canvas1.width = canvasWidth;
        canvas2.width = canvasWidth;
        canvas1.height = canvasHeight;
        canvas2.height = canvasHeight;
        canvas1.getContext("2d").clearRect(0, 0, canvas1.width, canvas1.height);
        canvas2.getContext("2d").clearRect(0, 0, canvas1.width, canvas1.height);

        new Chart(canvas1, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    data: avgTemperatureArr,
                    label: "Temperature",
                    borderColor: "#3e95cd",
                    fill: false
                }]
            },
            options: {
                title: {
                    display: false,
                    text: 'Average temperature'
                }
            }
        });

        new Chart(canvas2, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    data: avgVibrationArr,
                    label: "Vibration",
                    borderColor: "#cd1d43",
                    fill: false
                }]
            }
        });
    },

    renderStatusPanel: function () {

        // Last Sync date
        var lastSync = new Date(AllStats[AllStats.length - 1].date).toLocaleDateString("en-US");
        $('#last_sync_status').html(lastSync);
        // Last Sync time
        var interval =  new Date(AllStats[AllStats.length - 1].date);
        $('#last_sync_time').html(interval.toISOString().split('T')[1].split('.')[0])


    },

    renderAllStats: function() {
        Renderer.renderStatusPanel();
        Renderer.renderBlanket();
        Renderer.renderCabinet();
        Renderer.renderStatsPanel();
    },

    renderCabinet: function() {
        $('#cab_username').html(UserData.username);
        $('#cab_email').html(UserData.email);
        $('#cab_phone').html(UserData.phoneNbr === null ? 'Not defined' : UserData.phoneNbr);
        $('#cab_birth').html(UserData.birth === null ? 'Not defined' : UserData.birth);
    },

    renderBlanket: function () {
        var quantity = UserData.blanketsById !== null ? UserData.blanketsById.length : 0;
        $('#quantity_of_blankets').html(quantity);

        // blanket table
        var tc = "";
        for (var i = 0; i < UserData.blanketsById.length; ++i) {
            tc += "<tr><th>" + i + "</th><td>" + UserData.blanketsById[i].serialCode + "</td></tr>";
        }
        $('#blanket_tbody').html(tc);
    }
};

var DataLoader = {
    username: '',
    password: '',
    loadFlag: true,

    initialize: function() {
        $.ajaxSetup({
            headers: {
                'username': Credentials.username,
                'password': Credentials.password }
        });

    },

    load: function() {
        this.initialize();
        this.getUserData();
        //this.getCurrentBlanketsStatus();
    },

    getUserData: function() {

        $.ajax({
            url: '/rest/userdata/get',
            method: 'GET',
            headers: {
                'Username': Credentials.username,
                'Password': Credentials.password },
            success: function(responseData) {
                console.log("UserData: " + JSON.stringify(responseData));
                UserData = responseData;
                Renderer.renderGeneralUserData(responseData);
                DataLoader.getCurrentBlanketsStatus();
                DataLoader.getAllBlanketsStatuses();
            }
        });


    },

    getCurrentBlanketsStatus: function() {
        $.ajax({
            url: '/rest/userdata/currentstatus/' + UserData.blanketsById[0].id,
            method: 'GET',
            headers: {
                'Username': Credentials.username,
                'Password': Credentials.password },
            success: function(responseData) {
                console.log("Current Blanket Status: " + JSON.stringify(responseData));
                Renderer.renderGeneralUserData(responseData);
            }
        });
    },

    getAllBlanketsStatuses: function() {
        $.ajax({
            url: '/rest/userdata/allstatuses/' + UserData.blanketsById[0].id,
            method: 'GET',
            headers: {
                'Username': Credentials.username,
                'Password': Credentials.password },
            success: function(responseData) {
                console.log("Current Blanket Status: " + JSON.stringify(responseData));
                AllStats = responseData;
                Renderer.renderAllStats(responseData);
            }
        });
    }

};

var DataSender = {

    addBlanket: function(serialKey) {
        $.ajax({
            url: '/rest/blanket/addBlanket/' + UserData.id + '/' + serialKey,
            method: 'POST',
            headers: {
                'Username': Credentials.username,
                'Password': Credentials.password },
            success: function(response) {
                if (Boolean(response) !== true) {
                    EventHandler.showAddBlanketHelp();
                } else {
                    DataLoader.getUserData();
                    EventHandler.showAddBlanketSuccess();
                }
            }
        });
    },

    deleteBlanket: function(serialKey) {
        $.ajax({
            url: '/rest/blanket/removeBlanket/' + serialKey,
            method: 'POST',
            headers: {
                'Username': Credentials.username,
                'Password': Credentials.password },
            success: function(response) {
                if (Boolean(response) !== true) {
                    EventHandler.showRemoveBlanketHelp();
                } else {
                    DataLoader.getUserData();
                    EventHandler.showRemoveBlanketSuccess();
                }
            }
        });
    }

};





