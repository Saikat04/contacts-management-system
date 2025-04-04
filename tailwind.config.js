/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/**/*.{html,js}"],
  theme: {
    extend: {},
    screens: {
      'sm' : '460px', // Now you can use 'sm:hidden'
      'xs': '565px', // Now you can use 'xs:hidden'
    },
  },
  plugins: [],
  darkMode: 'class',
}

