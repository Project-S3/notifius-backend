//function notify(message) {
    var Url = 'https://www.gel.usherbrooke.ca/s3i/e20/';
    var notif = {
        type: 'basic',
        iconUrl: 'Notifus_Logo.png',
        title: 'Notifus',
        message: 'Tu as eu 49% a ton examen..'
    };
console.log("Extension and content scripts installed");
    chrome.notifications.create(Url, notif);

    chrome.notifications.onClicked.addListener(function (notificationId, byUser) {
        chrome.tabs.create({url: Url});
    });
//}