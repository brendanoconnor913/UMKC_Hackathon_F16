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

/**
 * Created by brendan on 11/12/16.
 */

class MaskGraphic extends GraphicOverlay.Graphic {
    private Paint mMaskPaint;
    private Paint mStatusPaint;
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
        mMaskPaint.setColor(Color.BLACK);
        mMaskPaint.setStyle(Paint.Style.STROKE);
        mMaskPaint.setStrokeWidth(15);
        mStatusPaint = new Paint();
        mStatusPaint.setColor(Color.GRAY);
        mStatusPaint.setStyle(Paint.Style.STROKE);
        mStatusPaint.setStrokeWidth(30);
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

        Bitmap smallLock = bl.createScaledBitmap(bl,150,150,true);
        Bitmap newGlasses = bp.createScaledBitmap(bp, (int)(scaleconst*bp.getWidth()),
                (int) (scaleconst*bp.getHeight()), true);
        Bitmap newMustache = bp.createScaledBitmap(bm, (int)(scaleconst*bm.getWidth()),
                (int) (scaleconst*bm.getHeight()), true);

        int gX = (int) ((width - newGlasses.getWidth())/2);
        int gY = (int) (3*((height - newGlasses.getHeight())/8));
        int mxShift = (int) ((newGlasses.getWidth() - newMustache.getWidth())/2);
        int myShift = (int) (1.1 * newGlasses.getHeight());
        int mX = gX + mxShift;
        int mY = gY + myShift;

        canvas.drawBitmap(newMustache,mX,mY,mMaskPaint);
        canvas.drawBitmap(newGlasses,gX,gY,mMaskPaint);

        double glasstocan = (newGlasses.getHeight()*newGlasses.getWidth())/(height*width);
//        Log.i("glass to canvas", Double.toString(glasstocan));

        double percentage = (face.getWidth()*face.getHeight())/(canvas.getWidth()*canvas.getHeight());
        Log.i("percent", Double.toString(percentage));
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
//        reMask(canvas, face, bm);
    }

    public void reMask(Canvas canvas, Face face, Bitmap object){
        //if euler faces are good enough

        double areaFace = face.getWidth()*face.getHeight();
        double areaScreen = canvas.getWidth()*canvas.getHeight();
        double beta = areaFace/areaScreen;

        double alpha = .75;

        if(beta == 0) {
            beta = alpha;
        }

        double w1 = Math.sqrt(alpha/beta)*face.getWidth();
        double h1 = Math.sqrt(alpha/beta)*face.getHeight();
        int ow = (int) Math.sqrt(alpha/beta)*object.getWidth();
        int oh = (int) Math.sqrt(alpha/beta)*object.getHeight();



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

        if(LM != null  && RM != null && N != null) {
            double x1 = (face.getWidth() - (LM.x-RM.x))/face.getWidth();
            double y1 = (face.getHeight() - N.y) / (face.getHeight());
            double y2 = 1 - N.y / face.getHeight();

            Log.i("xy nose ", N.x+" "+N.y);
            Log.i("facepos", face.getPosition().x + " " + face.getPosition().y);
            Log.i("proportions","x1: "+x1+" y1: "+y1+" y2: "+y2);

            float xNew = (float) ((canvas.getWidth() - w1) / 2 + x1 * w1);
            float yNew = (float) ((canvas.getHeight() - h1) / 2 + y1 * h1);
            PointF UpperLeft = new PointF(xNew, yNew);

            Log.i("upp", UpperLeft.x+ " " + UpperLeft.y);

            int wf = (int) w1;
            int hf = (int) h1;
            Bitmap newImage = object.createScaledBitmap(object, ow, oh, true);
            canvas.drawBitmap(object,translateX(UpperLeft.x),translateY(UpperLeft.y),mMaskPaint);

            canvas.drawLine((float)((canvas.getWidth()-w1)/2), (float)((canvas.getHeight()-h1)/2),
                    (float)((canvas.getWidth()-w1)/2), (float)(canvas.getHeight()-(canvas.getHeight()-h1)/2),
                    mMaskPaint);
        }
        //test it out
        //draw the new image
    }


}
