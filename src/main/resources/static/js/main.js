const bqwrap = $('#blockquote-wrap');
const sentinel = $('.sentinel');
let counter = 1;
let topPost = null;

function addPosts(channel, n) {

    console.log(`addPosts called with ${channel} and ${n}`);

    function appendPost(channel, id) {

        console.log(`Appending post ${id} from ${channel}`);

        var blockquote = $(document.createElement("blockquote"));
        blockquote.hide();
        blockquote.attr("class", "telegram-post");
        blockquote.attr("data-telegram-post", `${channel}/${id}`);
        $('#blockquote-wrap').append(blockquote);
        $.get(
            `/api/validMessage/${channel}/${id}`,
            function(data) {
                console.log(`Success! With data ${data}`);
                if (data === "true") {
                    console.log("Data was true, initing widget");
                    initWidget(blockquote[0]);
                } else {

                    console.log("Data was false, removing el");
                    blockquote.remove();
                    if (!(topPost !== null && id > topPost)) {
                        addPosts(channel, 1);
                    }
                }
            });
    }

    for (let i = 0; i < n; i++) {

        if (topPost !== null && counter > topPost) {
            console.log(`Id ${counter} larger than top post ${topPost}`);
            sentinel.remove();
            return;
        }

        appendPost(channel, counter);
        counter++;
    }

}

function paramsFromURL() {
    let params = window.location.pathname.split("/");
    return {
        channel: (params[1] !== "" && typeof params[1] != 'undefined' ) ? params[1] : null,
        from: (params[2] !== "" && typeof params[2] != 'undefined' ) ? params[2] : 1,
        to: (params[3] !== "" && typeof params[3] != 'undefined' )? params[3] : null
    };
}

function displayLanding() {

    $('#loader').hide();
    $('#welcome').show();

}

$(document).ready(function () {

    console.log("Let's go");
    console.log(window.location.hostname);
    console.log(window.location.host);

    var params = paramsFromURL();
    console.log("Params are:");
    console.log(params);

    if (params.channel === null) {

        displayLanding();

    } else {

        counter = params.from;
        topPost = params.to;

        $.get(
            `/api/topMessage?channel=${params.channel}`,
            function(data) {
                console.log(data);
                if (topPost == null) {
                    topPost = parseInt(data);
                }
                console.log(`Received top post! It was ${topPost}`);
            });


        addPosts(params.channel, 11);

        document.querySelector('#blockquote-wrap').appendChild(document.querySelector('.sentinel'));

        addPosts(params.channel, 5);

        const io = new IntersectionObserver(entries => {

            if (entries[0].intersectionRatio <= 0) {
                return;
            }

            document.querySelector('#blockquote-wrap').appendChild(document.querySelector('.sentinel'));

            addPosts(params.channel, 5)

        });

        io.observe(document.querySelector('.sentinel'));

        $('#loader').hide();
        document.querySelector('#blockquote-wrap').style.display = 'block';


    }

});