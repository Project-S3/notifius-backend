chrome.runtime.onInstalled.addListener(function (object) {
	//Ouvrir une page web lors de l'installation de l'extension
	if(object.reason === 'install')chrome.tabs.create({url: "https://www.gel.usherbrooke.ca/s3i/e20/"}, function (tab) {});
});
