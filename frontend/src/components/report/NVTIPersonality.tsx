
import personality from "../../assets/graphics/personality";

/**
 * An interface containing all of the properties expected of the props that are passed into the NVTIPersonality component.
 */
interface NVTIPersonalityProps {
  personality: {
    title: string;
    description: string;
  };
}

/**
 * Function that returns a NVTIPersonality component, 
 * which displays information on the user's watch history personality based on their NetflixViewingHistory.csv,
 * determined by our machine learning model in the backend.
 * @param props all of the information needed to display the user's watch history personality
 * @returns a NVTIPersonality component
 */
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
              <i>{personality.get(props.personality.title)?.description}</i>
            </p>
          </div>
        </div>
      </div>
    );
}