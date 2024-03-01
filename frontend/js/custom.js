// Define the function in the global scope (attached to the window object)
window.fadeOutElementAndNotifyServer = function() {
    // Send request to server after 500 milliseconds
    setTimeout(() => {
        fetch('/handleFadeOutCompletion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }, 500);
};
