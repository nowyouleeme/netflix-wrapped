interface PersonalityItem {
    graphic: JSX.Element,
    description: string
}
const personality = new Map<string, PersonalityItem>();

const explorer: PersonalityItem = {
  graphic: (
    <svg
      width="100%"
      height="100%"
      viewBox="0 0 200 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M143.6 120C144.4 113.4 145 106.8 145 100C145 93.2 144.4 86.6 143.6 80H177.4C179 86.4 180 93.1 180 100C180 106.9 179 113.6 177.4 120M125.9 175.6C131.9 164.5 136.5 152.5 139.7 140H169.2C159.6 156.5 144.3 169.3 125.9 175.6ZM123.4 120H76.6C75.6 113.4 75 106.8 75 100C75 93.2 75.6 86.5 76.6 80H123.4C124.3 86.5 125 93.2 125 100C125 106.8 124.3 113.4 123.4 120ZM100 179.6C91.7 167.6 85 154.3 80.9 140H119.1C115 154.3 108.3 167.6 100 179.6ZM60 60H30.8C40.3 43.4 55.7 30.6 74 24.4C68 35.5 63.5 47.5 60 60ZM30.8 140H60C63.5 152.5 68 164.5 74 175.6C55.7 169.3 40.3 156.5 30.8 140ZM22.6 120C21 113.6 20 106.9 20 100C20 93.1 21 86.4 22.6 80H56.4C55.6 86.6 55 93.2 55 100C55 106.8 55.6 113.4 56.4 120M100 20.3C108.3 32.3 115 45.7 119.1 60H80.9C85 45.7 91.7 32.3 100 20.3ZM169.2 60H139.7C136.5 47.5 131.9 35.5 125.9 24.4C144.3 30.7 159.6 43.4 169.2 60ZM100 0C44.7 0 0 45 0 100C0 126.522 10.5357 151.957 29.2893 170.711C38.5752 179.997 49.5991 187.362 61.7317 192.388C73.8642 197.413 86.8678 200 100 200C126.522 200 151.957 189.464 170.711 170.711C189.464 151.957 200 126.522 200 100C200 86.8678 197.413 73.8642 192.388 61.7317C187.362 49.5991 179.997 38.5752 170.711 29.2893C161.425 20.0035 150.401 12.6375 138.268 7.61205C126.136 2.58658 113.132 0 100 0Z"
        fill="url(#paint0_linear_11_424)"
      />
      <defs>
        <linearGradient
          id="paint0_linear_11_424"
          x1="0"
          y1="0"
          x2="200"
          y2="219"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#95C12B" />
          <stop offset="1" stopColor="#DB1864" />
        </linearGradient>
      </defs>
    </svg>
  ),
  description:
    "You’re an adventurous one! Not limiting yourself to either movies or shows, you delve into wide and varied range of genres to explore the unknown and go out of your comfort zone to find some hidden gems.",
};

const binge: PersonalityItem = {
  graphic: (
    <svg
      width="100%"
      height="100%"
      viewBox="0 0 200 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M40 0C28.9 0 20 8.9 20 20V100C20 111.1 28.9 120 40 120H160C171.1 120 180 111.1 180 100V20C180 8.9 171.1 0 160 0H40ZM40 20H160V100H40V20ZM20 130C8.9 130 0 138.9 0 150V180C0 191.1 8.9 200 20 200H180C191.1 200 200 191.1 200 180V150C200 138.9 191.1 130 180 130H20ZM60 150H180V180H60V150ZM70 157.5V172.5H110V157.5H70ZM130 157.5V172.5H170V157.5H130Z"
        fill="url(#paint0_linear_7_16)"
      />
      <defs>
        <linearGradient
          id="paint0_linear_7_16"
          x1="0"
          y1="0"
          x2="200"
          y2="219"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#DB1864" />
          <stop offset="1" stopColor="#95C12B" />
        </linearGradient>
      </defs>
    </svg>
  ),
  description: "You get things done and you get them done at once! Your enthusiasm for watchings shows and movies is unmatched, dedicating much of your time going down the Netflix rabbit hole while having it play in the background while doing work or staying in to relax.",
};

const old: PersonalityItem = {
  graphic: (
    <svg
      width="100%"
      height="100%"
      viewBox="0 0 200 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M100 0C45 0 0 45 0 100C0 155 45 200 100 200C155 200 200 155 200 100C200 45 155 0 100 0ZM106 118.5L90 110L50 87L58 74L91 93V50H106V110V118.5Z"
        fill="url(#paint0_linear_7_18)"
      />
      <defs>
        <linearGradient
          id="paint0_linear_7_18"
          x1="35"
          y1="170"
          x2="200"
          y2="-35"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7FC12B" />
          <stop offset="0.581584" stopColor="#DB1864" />
        </linearGradient>
      </defs>
    </svg>
  ),
  description: "You're an old soul at heart, constantly being drawn to and in tune with media of the past. Something about the old times resonants with you... perhaps you say that you were born in the wrong generation?",
};

const otaku: PersonalityItem = {
  graphic: (
    <svg
      width="100%"
      height="100%"
      viewBox="0 0 200 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M60 50H80V100H60V50ZM22.2 0H177.8C190 0 200 10 200 22.2V177.8C200 183.688 197.661 189.334 193.498 193.498C189.334 197.661 183.688 200 177.8 200H22.2C10 200 0 190 0 177.8V22.2C0 16.3122 2.33892 10.6655 6.50223 6.50223C10.6655 2.33892 16.3122 0 22.2 0ZM100 20C78.7827 20 58.4344 28.4285 43.4315 43.4315C28.4285 58.4344 20 78.7827 20 100C20 121.217 28.4285 141.566 43.4315 156.569C58.4344 171.571 78.7827 180 100 180C121.217 180 141.566 171.571 156.569 156.569C171.571 141.566 180 121.217 180 100C180 78.7827 171.571 58.4344 156.569 43.4315C141.566 28.4285 121.217 20 100 20ZM120 55H140V95H120V55ZM85 142.5C85 138.522 86.5804 134.706 89.3934 131.893C92.2064 129.08 96.0218 127.5 100 127.5C103.978 127.5 107.794 129.08 110.607 131.893C113.42 134.706 115 138.522 115 142.5V150H85V142.5Z"
        fill="url(#paint0_linear_7_14)"
      />
      <defs>
        <linearGradient
          id="paint0_linear_7_14"
          x1="0"
          y1="0"
          x2="200"
          y2="219"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#95C12B" />
          <stop offset="1" stopColor="#DB1864" />
        </linearGradient>
      </defs>
    </svg>
  ),
  description: "You have an unmatched love for anime and other Japanese pop culture! Whether it be because of the compelling characters, creative visuals, interesting world building, or exposure to Japanese history and language, you show clear appreciation for quality media.",
};

const lovebird: PersonalityItem = {
  graphic: (
    <svg
      width="100%"
      height="100%"
      viewBox="0 0 200 200"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M210 120H200C200 81.3 168.7 50 130 50H120V37.3C126 33.9 130 27.4 130 20C130 9 121.1 0 110 0C98.9 0 90 9 90 20C90 27.4 94 33.9 100 37.3V50H90C51.3 50 20 81.3 20 120H10C4.5 120 0 124.5 0 130V160C0 165.5 4.5 170 10 170H20V180C20 191.1 29 200 40 200H180C191.1 200 200 191.1 200 180V170H210C215.5 170 220 165.5 220 160V130C220 124.5 215.5 120 210 120ZM87 134.5L79.7 141.8L65 156.5L43 134.5C39 130.5 39 123.9 43 120C47.1 115.8 53.6 115.8 57.7 120L65 127.2L72.3 120C76.4 115.8 82.9 115.8 87 120C91 123.9 91 130.5 87 134.5ZM177 134.5L169.7 141.8L155 156.5L133 134.5C129 130.5 129 123.9 133 120C137.1 115.8 143.6 115.8 147.7 120L155 127.2L162.3 120C166.4 115.8 172.9 115.8 177 120C181 123.9 181 130.5 177 134.5Z"
        fill="url(#paint0_linear_7_17)"
      />
      <defs>
        <linearGradient
          id="paint0_linear_7_17"
          x1="190"
          y1="2.73219e-07"
          x2="199.099"
          y2="219.865"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#C1B22B" />
          <stop offset="1" stopColor="#DB18B0" />
        </linearGradient>
      </defs>
    </svg>
  ),
  description: "What a romanticist! You're attracted to shows and movies that show the beauty of human relationships and seeing how different personalities mesh with each other, and you're likely to be found daydreaming about your perfect partner.",
};

personality.set("The Explorer", explorer);
personality.set("The Otaku", otaku);
personality.set("The Lovebird", lovebird);
personality.set("The Binger", binge);
personality.set("The Old-School Watcher", old);

export default personality;