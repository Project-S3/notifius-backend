chrome.runtime.onInstalled.addListener(function(object){
    console.log("Extension and content scripts installed");
    chrome.tabs.create({url: "https://www.gel.usherbrooke.ca/s3i/e20/"}, function (tab) {
        console.log("New tab launched with http://yoursite.com/");
    });
});

