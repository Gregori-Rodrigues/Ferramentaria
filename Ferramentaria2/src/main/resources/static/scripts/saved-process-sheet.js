document.addEventListener('DOMContentLoaded', () => {
    const imageContainers = document.querySelectorAll('.imageContainer');
    imageContainers.forEach(container => {
        container.style.pointerEvents = 'none'; 
    });

    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.setAttribute('readonly', 'true'); 
    });

    const cameraButton = document.getElementById('cameraButton');
    if (cameraButton) cameraButton.remove();

    const saveButton = document.getElementById('save-button');
    if (saveButton) {
        saveButton.addEventListener('click', function(event) {
            event.preventDefault();
            window.print(); 
        });
    }
});
