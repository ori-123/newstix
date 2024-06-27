import {Category, Country, Language, UserRole} from "./Enums";

class User {
    constructor(
        public id: string,
        public username: string,
        public password: string,
        public roles: UserRole[],
        public language: Language,
        public country: Country,
        public category: Category,
        public timeframe: string
    ) {}
}

export default User;
