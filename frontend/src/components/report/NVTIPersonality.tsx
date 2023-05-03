
import personality from "../../assets/graphics/personality";

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
            {personality.get(props.personality.title)?.graphic}
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