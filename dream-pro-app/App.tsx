/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React, {useState} from 'react';
import {
  Alert,
  Dimensions,
  Image,
  ImageBackground,
  KeyboardAvoidingView,
  Pressable,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import InteractiveTextInput from 'react-native-text-input-interactive';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import {http} from './http';

const App = () => {
  const isDarkMode = useColorScheme() === 'dark';
  const [email, setEmail] = useState('');
  const [pass, setPass] = useState('');
  const [passConfirm, setPassConfirm] = useState('');
  const [loading, setLoading] = useState(false);
  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const onSubmit = () => {
    if (email.length === 0) {
      Alert.alert("Veuillez renseigner l'adresse email !");
      return;
    } else if (pass.length === 0) {
      Alert.alert('Veuillez renseigner le mot de passe !');
      return;
    } else if (passConfirm.length === 0) {
      Alert.alert('Veuillez confirmer le mot de passe !');
      return;
    } else if (pass !== passConfirm) {
      Alert.alert('Les mot de passes doivent correspondres');
      return;
    }
    setLoading(true);
    setTimeout(() => {
      http
        .post('/users', {
          email,
          password: pass,
        })
        .then(res => {
          console.log('res========');
          console.log(res);
          if (res.data && res.data.id) {
            Alert.alert('Enregistrement réussie !');
            setEmail('');
            setPass('');
            setPassConfirm('');
          } else if (
            res &&
            Array.isArray(res.data.errors) &&
            res.data.errors.length
          ) {
            Alert.alert(res.data.errors[0].userMessage);
          } else {
            Alert.alert("Erreur lors de l'enregistrement");
          }
          setLoading(false);
        });
    }, 1000);
  };

  return (
    <>
      <SafeAreaView style={backgroundStyle}>
        <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
        <ScrollView
          contentInsetAdjustmentBehavior="automatic"
          style={backgroundStyle}>
          <ImageBackground
            source={require('./assets/images/onboarding.png')}
            style={styles.backgroundImage}>
            <LinearGradient
              colors={['#000000', '#284e7f']}
              style={styles.gradient}>
              <SafeAreaView style={styles.container2}>
                <View style={{flex: 1, paddingVertical: 20}}>
                  <View style={{paddingVertical: 16}}></View>
                  <View
                    style={{
                      display: 'flex',
                      flexDirection: 'row',
                      justifyContent: 'center',
                    }}>
                    <Image
                      source={require('./assets/images/logo2.png')}></Image>
                  </View>

                  <View style={{paddingVertical: 16}}>
                    <Text
                      style={{
                        fontWeight: '900',
                        color: '#fff',
                        fontSize: 26,
                        textAlign: 'center',
                      }}>
                      {loading ? 'Chargement ...' : 'Création du compte'}
                    </Text>
                  </View>
                  {!loading && (
                    <>
                      <View style={{paddingVertical: 16}}>
                        <Text
                          style={{
                            fontWeight: '900',
                            color: 'rgba(255,255,255,.8)',
                            textAlign: 'center',
                            fontSize: 19,
                          }}>
                          Remplissez le formulaire pour accéder gratuitement à
                          la plateforme
                        </Text>
                      </View>
                      <View>
                        <KeyboardAvoidingView>
                          <InteractiveTextInput
                            mainColor={'#6b8099'}
                            value={email}
                            placeholder={'Adresse mail'}
                            secureTextEntry={false}
                            textInputStyle={{
                              backgroundColor: 'rgba(255,255,255,.1)',
                              borderWidth: 3,
                              height: 55,
                              borderRadius: 22,
                            }}
                            onChangeText={(text: string) => setEmail(text)}
                          />
                        </KeyboardAvoidingView>
                      </View>
                      <View style={{paddingVertical: 16}}>
                        <KeyboardAvoidingView>
                          <InteractiveTextInput
                            mainColor={'#6b8099'}
                            value={pass}
                            placeholder={'Mot de passe'}
                            secureTextEntry={true}
                            textInputStyle={{
                              backgroundColor: 'rgba(255,255,255,.1)',
                              borderWidth: 3,
                              height: 55,
                              borderRadius: 22,
                            }}
                            onChangeText={(text: string) => setPass(text)}
                          />
                        </KeyboardAvoidingView>
                      </View>
                      <View>
                        <KeyboardAvoidingView>
                          <InteractiveTextInput
                            mainColor={'#6b8099'}
                            value={passConfirm}
                            placeholder={'Confirmer le mot de passe'}
                            secureTextEntry={true}
                            textInputStyle={{
                              backgroundColor: 'rgba(255,255,255,.1)',
                              borderWidth: 3,
                              height: 55,
                              borderRadius: 22,
                            }}
                            onChangeText={(text: string) =>
                              setPassConfirm(text)
                            }
                          />
                        </KeyboardAvoidingView>
                      </View>
                      <View style={{flex: 2, justifyContent: 'center'}}>
                        <Pressable
                          style={({pressed}) => [
                            styles.button,
                            {opacity: pressed ? 0.6 : 1},
                          ]}
                          android_ripple={{color: 'lightgrey'}}
                          onPress={onSubmit}>
                          <Text style={styles.buttonText}>Commencer</Text>
                        </Pressable>
                      </View>
                    </>
                  )}
                </View>
              </SafeAreaView>
            </LinearGradient>
          </ImageBackground>
        </ScrollView>
      </SafeAreaView>
    </>
  );
};

const styles = StyleSheet.create({
  gradient: {
    flex: 1,
    opacity: 0.5,
  },
  container2: {
    flex: 1,
    paddingHorizontal: 25,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },

  container: {
    flex: 1,
  },
  backgroundImage: {
    flex: 1,
    resizeMode: 'cover',
    height: Dimensions.get('window').height,
  },
  loginForm: {
    position: 'absolute',
    top: 0,
    bottom: 0,
    left: 0,
    right: 0,
  },
  buttonContainer: {
    borderRadius: 8,
    margin: 16,
    marginTop: 8,
    elevation: 8,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 4,
    },
    shadowOpacity: 0.3,
    shadowRadius: 4.65,
  },
  button: {
    backgroundColor: '#284e7f',
    height: 58,
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    paddingHorizontal: 24,
    borderRadius: 16,
  },
  buttonText: {
    fontSize: 18,
    color: 'white',
    textAlign: 'center',
  },
  loaderStyle: {
    marginVertical: 16,
    alignItems: 'center',
  },
  spinnerTextStyle: {
    color: '#fff',
  },
});

export default App;
