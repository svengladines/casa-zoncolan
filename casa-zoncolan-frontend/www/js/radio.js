var mopidy;
var playList;

var connect = function ( callback ) {
	
	var server
		= "192.168.0.112";

	var remote
		= getParameter( window.location.href, "remote" );

	if ( remote ) {
		server = remote;
	}

	mopidy = new Mopidy({
		webSocketUrl: "ws://" + server + ":6680/mopidy/ws/"
	});

	mopidy.on(console.log.bind(console));
	mopidy.on( "websocket:incomingMessage", logEvent );
	mopidy.on("state:online", callback );
	
};

var tuneIn = function (  ) {
	
	var uri
		= getParameter( window.location.href, "q" );
	
	mopidy.playlists.lookup( uri ).then( function( list ) {
		renderPlayList( list );
	});
	
	addListeners();
	
};

var play = function ( ) {

	var uri
		= getParameter( window.location.href, "q" );

	mopidy.playlists.lookup( uri ).then( function( list ) {
		var tracks = list.tracks;
		clearTrackList();
		addToTrackList( tracks );
		playTrackList();
		renderPlayList( list );
	});
	
}

var stop = function ( ) {
	
	clearTrackList();
	
}


var clearTrackList = function() {

	mopidy.tracklist.clear();

};

var addToTrackList = function( tracks ) {

	mopidy.tracklist.add( tracks );

};

var playTrackList = function() {

	mopidy.tracklist.setRepeat( false);
	mopidy.tracklist.setConsume( true );
	mopidy.tracklist.setSingle( false );
	mopidy.tracklist.shuffle();
	mopidy.playback.next();
	mopidy.playback.play();		

};

var loadPlayLists = function() {
	
	mopidy.playlists.getPlaylists().then( renderPlayLists );
	
};

var renderPlayLists = function( playlists ) {

	var lists = $jq("#playlists");

	for ( i in playlists ) {

		var playlist = playlists[i];
		var listRef = $jq("<a/>").attr("href", "radio-playlist.html?q=" + encodeURI( playlist.uri ) ).addClass("playlist").html( playlist.name );
		var listItem = $jq("<li/>");
		listItem.append( listRef );
		lists.append( listItem );		

	}
	
};

var renderPlayList = function( playlist ) {

	var playListTemplate = $jq("#playListTemplate");
	var trackListTemplate = $jq("#trackListTemplate");

	mopidy.playback.getCurrentTlTrack().then( function( tltrack ) { $jq("#playList").html ( Mustache.render(playListTemplate, {playList: playlist, currentTrack: tltrack }) ); } );
	
	mopidy.tracklist.getLength().then( function( x ) { $jq("#trackList").html( Mustache.render(trackListTemplate, {playList: playlist, currentTrack: tltrack }) ) } );

};

var addListeners = function() {
	
	$jq("#play").click( function() { play() } );
	$jq("#stop").click( function() { stop() } );
	
}

var logEvent = function(x,y) {
	var e = event;
}