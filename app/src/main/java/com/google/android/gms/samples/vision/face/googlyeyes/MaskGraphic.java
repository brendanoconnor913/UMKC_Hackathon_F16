package com.google.android.gms.samples.vision.face.googlyeyes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PointF;
import android.util.Log;

import com.google.android.gms.samples.vision.face.googlyeyes.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

import static android.R.attr.x;
import static android.graphics.Path.Direction.CW;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by brendan on 11/12/16.
 */

class MaskGraphic extends GraphicOverlay.Graphic {
    private Paint mMaskPaint;
    private Paint mStatusPaint;
    private Paint mTargetPaint;
    private Paint mDeviationPaint;
    private Context context;
    private volatile PointF mPosition;
    private float width;
    private float height;
    private Face mFace = null;
    private static final float ID_Y_OFFSET = 0.0f;
    private static final float ID_X_OFFSET = 0.0f;
    private int timer = 0;
    boolean unlocked = false;


    //==============================================================================================
    // Methods
    //==============================================================================================

    MaskGraphic(GraphicOverlay overlay, Context c) {
        super(overlay);
        context = c;
        width = 0;
        height = 0;
        mMaskPaint = new Paint();
        mMaskPaint.setColor(Color.WHITE);
        mMaskPaint.setStyle(Paint.Style.STROKE);
        mMaskPaint.setStrokeWidth(15);
        mStatusPaint = new Paint();
        mStatusPaint.setColor(Color.GRAY);
        mStatusPaint.setStyle(Paint.Style.STROKE);
        mStatusPaint.setStrokeWidth(30);
        mTargetPaint = new Paint();
        mTargetPaint.setARGB(255, 102,205,170);
        mTargetPaint.setStyle(Paint.Style.STROKE);
        mTargetPaint.setStrokeWidth(15);
        mDeviationPaint = new Paint();
        mDeviationPaint.setARGB(255, 58,58,60);
        mDeviationPaint.setStyle(Paint.Style.STROKE);
        mDeviationPaint.setStrokeWidth(15);
    }

    /**
     * Updates the eye positions and state from the detection of the most recent frame.  Invalidates
     * the relevant portions of the overlay to trigger a redraw.
     */
    void updateMask(PointF position, Face face) {
        mPosition = position;
        mFace = face;
        postInvalidate();
    }

    /**
     * Draws the current eye state to the supplied canvas.  This will draw the eyes at the last
     * reported position from the tracker, and the iris positions according to the physics
     * simulations for each iris given motion and other forces.
     */
    @Override
    public void draw(Canvas canvas) {
        PointF detectPosition = mPosition;
        height = canvas.getHeight();
        width = canvas.getWidth();

        if ((detectPosition == null)) {
            timer = 0;
            return;
        }

        PointF position =
                new PointF(translateX(detectPosition.x), translateY(detectPosition.y));

        // Use the inter-eye distance to set the size of the eyes.
//        float distance = (float) Math.sqrt(
//                Math.pow(rightPosition.x - leftPosition.x, 2) +
//                        Math.pow(rightPosition.y - leftPosition.y, 2));
//        float eyeRadius = EYE_RADIUS_PROPORTION * distance;
//        float irisRadius = IRIS_RADIUS_PROPORTION * distance;

        // Advance the current left iris position, and draw left eye.
//        PointF leftIrisPosition =
//                mLeftPhysics.nextIrisPosition(leftPosition, eyeRadius, irisRadius);
//        drawEye(canvas, leftPosition, eyeRadius, leftIrisPosition, irisRadius, mLeftOpen);
//
//        // Advance the current right iris position, and draw right eye.
//        PointF rightIrisPosition =
//                mRightPhysics.nextIrisPosition(rightPosition, eyeRadius, irisRadius);
//        drawEye(canvas, rightPosition, eyeRadius, rightIrisPosition, irisRadius, mRightOpen);




        drawMask(canvas,position, mFace);
    }

    /**
     * Draws the eye, either closed or open with the iris in the current position.
     */
    private void drawMask(Canvas canvas, PointF position, Face face) {
//        Path p = new Path();
//        p.addCircle(1000,1000,20, Path.Direction.CW);
//        float x = 200;
//        float y = 400;
//        p.addArc(x,750,y,900,0, -45); // left, top, right, bottom, startA, sweepA
//        canvas.drawPath(p,mMaskPaint);

        // Draws a circle at the position of the detected face, with the face's track id below.
//        float x = translateX(face.getPosition().x + face.getWidth() / 2);
//        float y = translateY(face.getPosition().y + face.getHeight() / 2);
        // Draws Text
////        canvas.drawText("id: " + mFaceId, x + ID_X_OFFSET, y + ID_Y_OFFSET, mIdPaint);
//        canvas.drawText("happiness: " + String.format("%.2f", face.getIsSmilingProbability()), x - ID_X_OFFSET, y - ID_Y_OFFSET, mMaskPaint);
//        canvas.drawText("right eye: " + String.format("%.2f", face.getIsRightEyeOpenProbability()), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mMaskPaint);
//        canvas.drawText("left eye: " + String.format("%.2f", face.getIsLeftEyeOpenProbability()), x - ID_X_OFFSET*2, y - ID_Y_OFFSET*2, mMaskPaint);
//
//        // Draws a bounding box around the face.
//        float xOffset = scaleX(face.getWidth() / 2.0f);
//        float yOffset = scaleY(face.getHeight() / 2.0f);
//        float left = x - xOffset;
//        float top = y - yOffset;
//        float right = x + xOffset;
//        float bottom = y + yOffset;
//        canvas.drawRect(left, top, right, bottom, mMaskPaint);

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.drawable.mustache2);
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),R.drawable.glasses);
        Bitmap bl = BitmapFactory.decodeResource(context.getResources(), R.drawable.lock);

        double scaleconst = .66; // need to get working in terms of canvas
//        if(!canvas.equals(null)) {
//            Log.i("working", "test");
//        }

        Bitmap smallLock = bl.createScaledBitmap(bl,150,150,true);
//        Bitmap newGlasses = bp.createScaledBitmap(bp, (int)(scaleconst*bp.getWidth()),
//                (int) (scaleconst*bp.getHeight()), true);
//        Bitmap newMustache = bp.createScaledBitmap(bm, (int)(scaleconst*bm.getWidth()),
//                (int) (scaleconst*bm.getHeight()), true);
//
//        int gX = (int) ((width - newGlasses.getWidth())/2);
//        int gY = (int) (3*((height - newGlasses.getHeight())/8));
//        int mxShift = (int) ((newGlasses.getWidth() - newMustache.getWidth())/2);
//        int myShift = (int) (1.1 * newGlasses.getHeight());
//        int mX = gX + mxShift;
//        int mY = gY + myShift;

//
//
//        double glasstocan = (newGlasses.getHeight()*newGlasses.getWidth())/(height*width);
////        Log.i("glass to canvas", Double.toString(glasstocan));

        double percentage = (face.getWidth()*face.getHeight())/(canvas.getWidth()*canvas.getHeight());
//        Log.i("percent", Double.toString(percentage));
        if(percentage >= 0.017 &&  percentage <= .039) {
            timer++;
            if(timer >= 8) {
                unlocked = true;
                canvas.drawLine(0,0,canvas.getWidth(),0,mStatusPaint);
            }
            else {
                canvas.drawLine(0,0,(float)((canvas.getWidth()/8)*timer),0,mStatusPaint);
            }
        }
        else {
            canvas.drawLine(0,0,(float)(canvas.getWidth()/8),0,mStatusPaint);
            timer = 0;
        }
        if(unlocked) {
            canvas.drawBitmap(smallLock, 1100, 50, mMaskPaint);
        }
        drawBounds(canvas,face);
    }


    private void drawBounds(Canvas canvas, Face face){
        //EulerConstraint
        if(face.getEulerY() < 10 && face.getEulerY() >-10 &&
                face.getEulerZ() <10 && face.getEulerZ()>-10){
            double areaFace = face.getWidth()*face.getHeight();
            double areaScreen = canvas.getWidth()*canvas.getHeight();
            double beta = areaFace/areaScreen;

            double alpha = .5;

            //this is in case we get no face on accident
            if(beta == 0) {
                beta = alpha;
            }

            //find the new rectangle bounds for right scale
            double newWidth = face.getWidth() * sqrt(alpha/beta);
            double newHeight = face.getHeight() * sqrt(alpha/beta);

            // define threshold values
            double threshold = .30;
            double threshLowW = newWidth*Math.sqrt(1-threshold);
            double threshLowH = newHeight*Math.sqrt(1-threshold);
            double threshHihW = newWidth*Math.sqrt(1+threshold);
            double threshHihH = newHeight*Math.sqrt(1+ threshold);

            //draw centered bounds
            float xMargin = (float)(canvas.getWidth() - newWidth)/2;
            float yMargin = (float)(canvas.getHeight()- newHeight)/2;

            double topLeftx = xMargin;
            double topLefty = yMargin;

            double topRightx = xMargin + newWidth;
            double topRighty = yMargin;

            double bottomLeftx = xMargin;
            double bottomLefty = yMargin+newHeight;

            double bottomRightx = xMargin + newWidth;
            double bottomRighty = yMargin + newHeight;

            float xMarginL = (float)(canvas.getWidth() - threshLowW)/2;
            float yMarginL = (float)(canvas.getHeight()- threshLowH)/2;

            float xMarginH = (float)(canvas.getWidth() - threshHihW)/2;
            float yMarginH = (float)(canvas.getHeight() - threshHihH)/2;

            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.drawable.mustache2);
            Bitmap bp = BitmapFactory.decodeResource(context.getResources(),R.drawable.glasses);

            Bitmap newMustache = null;

            //rescale moustache
            //get landmarks
            PointF LM = null;
            PointF RM = null;
            PointF N = null;
            for(Landmark landmark: face.getLandmarks()){
                if(landmark.getType() == 5){ //leftmouth
                    LM = landmark.getPosition();
                }
                if(landmark.getType() == 11){ //rightmouth
                    RM = landmark.getPosition();
                }
                if(landmark.getType() == 10) { //nose
                    N = landmark.getPosition();
                }

            }

            int gX=0;
            int gY=0;


            float x = translateX(face.getPosition().x + face.getWidth() / 2);

            if(LM != null  && RM != null && N != null) {
                int newX = (int)(Math.abs(RM.x - LM.x)*Math.sqrt(alpha/beta));
                int newY = (int)(Math.abs(LM.y - N.y)*Math.sqrt(alpha/beta));
                newMustache = bp.createScaledBitmap(bm, newX,
                        newY, true);

                float faceX = translateX(face.getPosition().x);
                float faceXEnd = translateX(face.getPosition().x + face.getWidth());
                float faceY = translateY(face.getPosition().y);
                float faceYEnd = translateY(face.getPosition().y + face.getHeight());

                float noseY = translateY(N.y);
                float lmX = translateX(LM.x);
                float rmX = translateX(RM.x);
                float mX = (lmX + rmX)/2;

                gX = (int) (xMargin + Math.abs((faceXEnd-lmX)/(faceXEnd - faceX))*newWidth);
                gY = (int) ((yMargin + Math.abs((noseY-faceY)/(faceYEnd-faceY))*newHeight)+.15*newHeight);

            }

            Bitmap newGlasses = bp.createScaledBitmap(bp, (int)(.6*newWidth),
                    (int) (.2*newHeight), true);


            if(!(newMustache == null)) {
                int mxShift = (int) ((newGlasses.getWidth() - newMustache.getWidth())/2);
                int myShift = (int) (1 * newGlasses.getHeight());
                int mX = gX - mxShift;
                int mY = gY - myShift;
                canvas.drawBitmap(newMustache,gX,gY,mMaskPaint);
                canvas.drawBitmap(newGlasses,mX,mY,mMaskPaint);
            }

            // Draws Threshold Boxes
            drawRect(xMargin, yMargin, (float) newWidth, (float) newHeight, mTargetPaint, canvas);
            drawRect(xMarginH, yMarginH, (float) threshHihW, (float) threshHihH, mDeviationPaint, canvas);
            drawRect(xMarginL, yMarginL, (float) threshLowW, (float) threshLowH, mDeviationPaint, canvas);



            // Draws a circle at the position of the detected face, with the face's track id below.
            float fx = translateX(face.getPosition().x + face.getWidth() / 2);
            float y = translateY(face.getPosition().y + face.getHeight() / 2);
            // Draws Text
//        // Draws a bounding box around the face.
            float xOffset = scaleX(face.getWidth() / 2.0f);
            float yOffset = scaleY(face.getHeight() / 2.0f);
            float left = fx - xOffset;
            float top = y - yOffset;
            float right = fx + xOffset;
            float bottom = y + yOffset;
            canvas.drawRect(left, top, right, bottom, mMaskPaint);

        }
    }

    private void drawRect(float mX, float mY, float w, float h, Paint p, Canvas c ){
        c.drawLine(mX, mY, mX + w, mY, p);
        c.drawLine(mX + w, mY, mX+w, mY +h, p);
        c.drawLine(mX+w,mY+h,mX,mY+h,p);
        c.drawLine(mX,mY+h,mX,mY,p);
    }

    private void landMarkTester(Face face){
        Log.i("face location", face.getPosition().x + "," + face.getPosition().y);
        for(Landmark landmark: face.getLandmarks()){
            if(landmark.getType() == 4) {
                Log.i("landmark",landmark.getPosition().x + "," + landmark.getPosition().y);
                //  Log.i("face location", face.getPosition().x + "," + face.getPosition().y);
                //Log.i("landmark translate", translateX(landmark.getPosition().x) + ", " + translateY(landmark.getPosition().y));
            }
            if(landmark.getType() == 10) {
                Log.i("landmark 10",landmark.getPosition().x + "," + landmark.getPosition().y);
                //Log.i("face location 10", face.getPosition().x + "," + face.getPosition().y);
               // Log.i("landmark translate 10", translateX(landmark.getPosition().x) + ", " + translateY(landmark.getPosition().y));
            }
        }
    }

}
