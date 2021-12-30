import { ApplicationRole } from "./ApplicationRole";
export class ApplicationUser {
    username!: String;
    password!: String;
    applicationRoles!:Array<ApplicationRole>
}