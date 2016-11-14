# UMKC_Hackathon_F16
EyeVerify biometric source capture

Problem Statement:

For image based biometrics it is important to capture a good quality image of the biometric source. Users often struggle to correctly position the phone for this image capture to take place. In many cases users cancel the biometric authentication process because they refuse to try or struggle to correctly position the phone.

Objective:

Design an image capture process and UI that makes it easy for users to easily position the phone to capture the face and eye regions.

Approach:

Since this was a UI problem our focus was on implementing the most simple UI as possible to get users to properly adjust their camera relative to their face.
The way that we found to most effectively do this was by placing images for users to put on their face. This made for an intuitive and fun way for users to focus on how their face looks relative to the screen.
By adjusting the size of the images relative to the size of their face and the screen that is being used to display their face we can ensure that the users face is adequately positioned to capture image data for their eyes.
Once the user's face is in position we have a time banner that scrolls across the screen. Once the scroll fills the width of the screen a lock appears to indicate the user has held the camera in the correct position for a long enough period of time.
After that occurs the lock stays on screen until the face is no longer identified essentially "locking" again. For facial recognition we utilized Google's facial detection sdk found here: https://developers.google.com/vision/face-detection-concepts.
