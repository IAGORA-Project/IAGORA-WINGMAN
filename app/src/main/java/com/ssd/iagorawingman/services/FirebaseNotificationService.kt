package com.ssd.iagorawingman.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.ui.main_menu.MainActivity
import com.ssd.iagorawingman.ui.receive_order.ReceiveOrderActivity
import org.json.JSONObject
import kotlin.random.Random
import android.net.Uri
import com.ssd.iagorawingman.utils.TriggerWhatsApp


class FirebaseNotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        println("DJDHJDKHDKJDHJD $remoteMessage")

        var pendingIntent: PendingIntent? = null
        val notificationID = Random.nextInt()
        var messageTitle: String? = ""
        var messageBody: String? = ""
        var messageImageUrl: String? = ""


        //  Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            messageTitle = remoteMessage.notification!!.title
            messageBody = remoteMessage.notification!!.body
            messageImageUrl = remoteMessage.notification!!.imageUrl.toString()
        }

        println("DKJDJKDHJKDDJ $remoteMessage")
        val ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            if(remoteMessage.data["type"] == "new-order") {
                try {
                    val notification = remoteMessage.data["details"]
                    val intent = Intent(this, ReceiveOrderActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("data-notif", notification)
                    startActivity(intent)


//                    val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6288261323037&text=Hallo Wingman"))
//                    startActivity(intent2)
//                    TriggerWhatsApp.openWhatsApp(
//                        "6288261323037",
//                        "Hallo Wingman, saya bisa tawar belanjaan saya pada nomor order ?",
//                        this.packageManager,
//                        this
//                    )
                } catch (e: Exception) { }
            }

            Log.d("CEKDDATAAAA", "Message data: " + remoteMessage.data)
        }


        if(messageBody != null && messageTitle != null){
            showNotification(messageTitle, messageBody, messageImageUrl, remoteMessage.data)
        }
    }



    private fun showNotification(title: String?, messageBody: String?, imageUrl: String?, data: Map<String, String>? = null) {
        val channelId = "push-notification-Iagora-Wingman"
        val channelName = "Iagora-Wingman-Notification"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        var pendingIntent: PendingIntent? = null
        val intent = Intent(this, MainActivity::class.java)

        val notification = data?.get("notification")
        val dataNotification = data?.get("customerdata")
        val json = JSONObject(notification)

//        val soundUri: Uri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.sound_notification)
        val soundUriEcek = Uri.parse("android.resource://" +  applicationContext.packageName + "/" + R.raw.sound)


        // create notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(mChannel)
        }

        if(data?.get("type") == "new-order") {
            pendingIntent = dataNotification?.let { dataNotif -> openReceiveOrder(notificationID, dataNotif) }
        }else{
            pendingIntent = PendingIntent.getActivity(this, notificationID /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)




        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_close)
            .setColor(ContextCompat.getColor(applicationContext, R.color.redPrimary))
            .setContentTitle(json.getString("title"))
            .setContentText(json.getString("body"))
            .setAutoCancel(true)
            .setOngoing(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setLargeIcon(imageNotifToBitmap(json.getString("image"))) // untuk icon gambar samping kanan
            .setStyle(
                if(json.getString("image").length > 4)  NotificationCompat.BigPictureStyle().bigPicture(imageNotifToBitmap(json.getString("image")))
                else null
            ) // untuk gambar besar di body

        notificationManager.notify(notificationID, notificationBuilder.build())

    }


    private fun imageNotifToBitmap(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null

        try{
            val futureTarget = Glide
                .with(this)
                .asBitmap()
                .load(imageUrl)
                .submit()
            bitmap = futureTarget.get()
        }catch (e: Exception) {}

        return bitmap
    }



    // Open Receive Order Activity
    private fun openReceiveOrder(notificationID: Int, data: String): PendingIntent {
        val intent = Intent(this, ReceiveOrderActivity::class.java)
        intent.putExtra("data-notif", data)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return  PendingIntent.getActivity(this, notificationID /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        println("NEWTOKENGENERATE $token")
    }
}