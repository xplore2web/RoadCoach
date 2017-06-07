/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.utility;

/**
 *
 * @author AR
 */
public interface IConstants {
    
    //This is for Account preferences.
    final static String kContactGroupMindbodyKey = "mindbody";
    final static String kContactGroupAccountKey = "Account";
    final static String kAccountPreferencesTableKey = "company_preferences";
    final static String kAccountPreferencesMindbodyKey = "mindbody_email_list";
    final static String kEmailAddressAccountPreferenceKey = "contactGroups";
    final static String kContactGroupNameKey = "contactGroupName";
    final static String kEmailLastNameKey = "emailLastName";
    final static String kEmailFirstNameKey = "emailFirstName";
    final static String kEmailClientName = "emailClientName";
    final static String kEmailClientFirstName = "&lt;clientFirstName&gt;";
    final static String kEmailClientLastName = "&lt;clientLastName&gt;";
    final static String kEmailClientFullName = "&lt;clientFullName&gt;";
    final static String kContactGroupDescription = "listDescription";
    final static String kEmailUIDKey = "emailUID";
    final static String kContactGroupDefaultFromName = "defaultFromName";
    final static String kContactGroupID = "contactGroupID";
    final static String kContactGroupListDescription = "listDescription";
    final static String kContactGroupAddedDate = "contactGroupAddedDate";
    final static String kEmailAddressesKey = "emailAddresses";
    final static String kEmailMindbodyEmailAddresses = "mindbody_emailAddresses";
    final static String kEmailAddressKey = "emailAddress";
    final static String kEmailAddressAddedKey = "addedDate";
    final static String kEmailKey = "Email";
    final static String kFacebookKey = "Facebook";
    final static String kTwitterKey = "Twitter";
    final static String kEmailSettings = "emailSettings";
    final static String kEmailFromAddress = "from_address";
    final static String kEmailReplyAddress = "reply_email_address";
    final static String kFromName = "from_name";
    final static String kPromotionActionsKey = "actions";
    final static String kColors = "colors";
    final static String kPromotionCampaignTemplateTillDate = "tillDate";
    final static String kPromotionCampaignTemplateDays = "days";
    final static String kPromotionCampaignTemplateDescription = "description";
    final static String kPromotionCampaignTemplateTime = "time";
    final static String kPromotionCampaignTemplateIsRecurring = "isRecurring";
    final static String kPromotionCampaignTemplateTitle = "title";
    final static String kPromotionCampaignTemplateEntityType = "type";
    final static String kFacebookDescriptionKey = "description";
    final static String kFacebookPostTextKey = "post_text";
    final static String kFacebookUrlKey = "url";
    final static String kFacebookLinkTitleKey = "title";
    final static String kFacebookManagedPageKey = "ManagedPage";
    final static String kSocialPostCompleteStatus = "complete";
    final static String kSocialPostTemplateSavedStatus = "template_saved";
    final static String kSocialPostapprovedStatus = "approved";
    final static String kAccountPromotionCampaignOpenStatus = "open";
    final static String kTwitterTextKey = "text";
    final static String kTwitterURLKey = "shorturl";
    final static String kFooters = "AccountProfile";
    final static String kUnsubscribeEmails = "unsubscribeEMails";
    final static String kFooterDetailsFacebookUrl = "facebookUrl";
    final static String kFooterDetailsTwitterUrl = "twitterUrl";
    final static String kFooterDetailsWebsiteUrl = "websiteUrl";
    final static String kFooterDetailsInstagramUrl = "instagramUrl";
    final static String KAccountPROFILECOLOR = "AccountProfileColor";
      
    // for external source 
    final static Integer EXTERNAL_SOURCE_NON_MINDBODY = 0;
    final static Integer EXTERNAL_SOURCE_MINDBODY = 1;
    final static int HTTPSuccessCode = 205;
    
    final static String kNoReplyAleaf = "no_reply@aleaf.com";
    final static String kContentHTML = "text/html";
    
    // for marketing program 
    final static String PROMOTION_CAMPAIGN_DIVISION = "MP";
    final static String PROMOTION_CAMPAIGN__ACTION_DIVISION = "MPA";
    final static String PROMOTION__UPSELL_ACTION_DIVISION = "MPRA";
    final static String DIRECT_EMAIL_DIVISION = "DE";
    
    // for  pushed scheduled action companies
    final static String ACTION_COMPANIES_SENT_STATUS = "Sent";
    final static String ACTION_COMPANIES_READY_TO_GO = "Ready to go";
     final static String ACTION_COMPANIES_NO_EMAIL_TAG_CONFIGURED = "No email tag configured";
     // support email id
     final static String SUPPORT_ALEAF_EMAIL_ID = "requests@aleaf.com";
     
     // email template file names and other related constants
     final static String ETActionAssignmentEmailTemplate = "actionassignmentemailtemplate.html";
     final static String ETEmailTagReminderTemplate = "emailtagremindertemplate.html";
     final static String ETInviteAccountTemplate = "inviteAccountemailtemplate.html";
     final static String ETBillingStatusReminderTemplate = "billingstatusremindertemplate.html";
     final static String ETNameOfPersonWhoAsssigned = "*Name of person who assigned*";
     final static String ETActionType = "*action type*";
     final static String ETCompanyName = "*company*";
     final static String ETPromotionActionName = "*Marketing Action Name*";
     final static String ETPromotionCampaignName = "*Marketing Campaign Name*";
     final static String ETPromotionActionDate = "*Marketing Action Date*";
     final static String ETPromotionActionStatus = "*Marketing Action Status*";
     final static String EThost = "host";
     final static String ETHeadAccountName = "*Corporate Head Account Name*";
     final static String ETTagName = "*tag*";
     final static String ETNewInvitedAccountURLFileName= "Accountregistration";
     final static String ETInviteExistingAccountURLFileName= "signin";
     final static String ETInviteAccountHostName = "hostName";
     final static String ETInviteAccountfileName = "fileName";
     final static String ETInviteAccounthashURL = "hashURL";
     
     // Operation name
     public static final Integer OPERATION_CREATED_ACTION_ID = 1;
     public static final Integer OPERATION_ASSIGNED_TO_ID = 2;
     public static final Integer OPERATION_REASSIGNED_TO_ID = 3;
     public static final Integer OPERATION_ADDED_TEMPLATE_ID = 4;
     public static final Integer OPERATION_UPDATED_TEMPLATE_ID = 5;
     public static final Integer OPERATION_REMOVED_TEMPLATE_ID = 6;
     public static final Integer OPERATION_UPDATED_ACTION_ID = 7;
     public static final Integer OPERATION_APPROVED_ACTION_ID = 8;
     public static final Integer OPERATION_DISAPPROVED_ACTION_ID = 9;
     public static final Integer OPERATION_DELETED_COMMENT_ACTION_ID = 10;
     public static final Integer OPERATION_PLAY_ACTION_ID = 11;
     public static final Integer OPERATION_PAUSE_ACTION_ID = 12;
     
     //Billing Constants
     public static final String KBILLINGSTATUS = "billingStatus";
     public static final String KACCOUNTSTATUS = "accountStatus";
     public static final Integer KSTARTERPLANNUMBEROFCONTACTS = 2500;
     public static final Integer KBASICPLANNUMBEROFCONTACTS = 5000;
     public static final Integer KPLUSPLANNUMBEROFCONTACTS = 10000;
     public static final Integer KPROPLANNUMBEROFCONTACTS = 15000;
     public static final String KBILLINGBASICPLAN = "Basic";
     
     //Account Status
     public static Integer  PASSWORD_SET_STATUS = 1;
     public static Integer PASSWORD_RESET_STATUS = 2;
     //Email Histroy
     public static final Integer KTHREEYEARSAGO = -730;
     
     //Verification constants
     public static final String kVERIFICATION = "VERIFICATION";
     public static final String kACCOUNTS = "ACCOUNTS";
}
