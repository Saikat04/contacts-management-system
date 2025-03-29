console.log("Script loaded successfully");

let currentTheme = getTheme();
changeTheme(currentTheme);

//todo
function changeTheme(theme) {
     //set the lisitener for the theme toggle button
     const changeThemeButton = document.querySelector("#theme-toggle");
        changeThemeButton.addEventListener("click", function() {
            if (theme === "dark") {
                theme = "light";
            } else {
                theme = "dark";
            }
            document.querySelector('html').classList.remove(theme === "dark" ? "light" : "dark");
            document.querySelector('html').classList.add(theme);
            setTheme(theme);
        });
}
//set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

//get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "dark";
}