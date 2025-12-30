const UserProfile = ({age, name, gender,userNumber, ...props}) => {
    gender = gender === "FEMALE" ? "women" : "men";
    return (
        <div>
            <h2>{name}</h2>
            <p>{age}</p>
            <img
                src={`https://randomuser.me/api/portraits/${gender}/${userNumber}.jpg`}
            />
            {props.children}
        </div>
    )
}

export default UserProfile;