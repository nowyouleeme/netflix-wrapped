interface NVTIPersonalityProps {
  personality: {
    title: string;
    description: string;
  };
}

export function NVTIPersonality(props: NVTIPersonalityProps) {
    // we will hold svg in front end? 
    return (
      <div className="center WrappedPersonality">
        <div className="center PersonalityContainer">
          <p className="personalityBigP">Meet your NVTI personality:</p>
          <div className="personalityGraphic">
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
          </div>
          <p className="personalityTitle">✧ {props.personality.title} ✧</p>
          <div className="personalityDesc">
            <p className="personalityDescP">
              <i>{props.personality.description}</i>
            </p>
          </div>
        </div>
      </div>
    );
}