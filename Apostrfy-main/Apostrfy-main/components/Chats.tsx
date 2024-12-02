import React, { useState, useEffect } from 'react';
import { View, TextInput, StyleSheet, FlatList, TouchableOpacity, Text, Image, StatusBar } from 'react-native';
import { Swipeable } from 'react-native-gesture-handler';
import BottomNavigationBar from './BottomNavigationBar';
import Header from './Header';
import Icon from 'react-native-vector-icons/FontAwesome';
import Foundation from '@expo/vector-icons/Foundation';
import Ionicons from '@expo/vector-icons/Ionicons';
import Entypo from '@expo/vector-icons/Entypo';

// Sample chat data
const sampleConnections = [
  { id: '1', name: 'John Doe', lastMessage: 'Hey, how are you?', profileImage: require('@/assets/images/Avatar.jpg'), time: '20:25' },
  { id: '2', name: 'Jane Smith', lastMessage: 'See you soon!', profileImage: require('@/assets/images/Avatar2.jpg'), time: '18:25' },
  { id: '3', name: 'John Doe', lastMessage: 'Hey, how are you?', profileImage: require('@/assets/images/Avatar3.jpg'), time: '15:40' },
  { id: '4', name: 'John', lastMessage: 'Hey, how are you?', profileImage: require('@/assets/images/Avatar4.jpg'), time: '20:00' },
  { id: '5', name: 'Manish', lastMessage: 'Hey, how are you?', profileImage: require('@/assets/images/Default-Image.png'), time: '21:05' },
];

const Chats = ({ navigation }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [connections, setConnections] = useState([]);
  const [archivedConnections, setArchivedConnections] = useState([]);
  const [selectedIds, setSelectedIds] = useState([]);
  const [isLongPressed, setIsLongPressed] = useState(false);

  const sortConnectionsByTime = (connections) => {
    return connections.slice().sort((a, b) => {
      const [hourA, minuteA] = a.time.split(':').map(Number);
      const [hourB, minuteB] = b.time.split(':').map(Number);
      return hourB - hourA || minuteB - minuteA; // Compare hours first, then minutes
    });
  };

  useEffect(() => {
    setConnections(sortConnectionsByTime(sampleConnections));
  }, []);

  const filteredConnections = connections.filter(connection =>
    connection.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const archiveChat = (itemId) => {
    const newConnections = connections.filter(connection => connection.id !== itemId);
    const selectedChat = connections.find(connection => connection.id === itemId);
    
    // Update the archived chats state
    if (selectedChat) {
      setArchivedConnections(prevArchivedConnections => [...prevArchivedConnections, selectedChat]);
    }
    
    // Update the connections state
    setConnections(sortConnectionsByTime(newConnections));
  };

  const deleteChat = (itemId) => {
    setConnections(prevConnections => prevConnections.filter(connection => connection.id !== itemId));
  };

  const renderChatItem = ({ item }) => {
    const renderRightActions = () => (
      <View style={styles.actionButtonsContainer}>
        <TouchableOpacity onPress={() => archiveChat(item.id)} style={styles.archiveButton}>
          <Foundation name="archive" size={24} color="white" />
        </TouchableOpacity>
        <TouchableOpacity onPress={() => deleteChat(item.id)} style={styles.deleteButton}>
          <Ionicons name="trash" size={24} color="white" />
        </TouchableOpacity>
        <TouchableOpacity style={styles.moreOptionsButton}>
          <Entypo name="dots-three-vertical" size={24} color="white" />
        </TouchableOpacity>
      </View>
    );

    return (
      <Swipeable renderRightActions={renderRightActions}>
        <TouchableOpacity
          style={[styles.chatItem, { backgroundColor: selectedIds.includes(item.id) ? '#ADD8E6' : '#F9F9F9' }]}
          onPress={() => navigation.navigate('MessageScreen', { chatData: item })}
        >
          <Image source={item.profileImage} style={styles.profilePicture} />
          <View style={styles.chatContent}>
            <View style={styles.chatHeader}>
              <Text style={styles.userName}>{item.name}</Text>
              <Text style={styles.chatTime}>{item.time}</Text>
            </View>
            <Text style={styles.lastMessage}>{item.lastMessage}</Text>
          </View>
        </TouchableOpacity>
      </Swipeable>
    );
  };

  const renderArchivedSection = () => (
    <TouchableOpacity style={styles.archiveSection} onPress={() => navigation.navigate('ArchivedChats', { archivedChats: archivedConnections, setArchivedChats: setArchivedConnections, setConnections: setConnections })}>
      <Foundation name="archive" size={20} color="black" />
      <Text style={styles.archiveText}>Archived ({archivedConnections.length})</Text>
    </TouchableOpacity>
  );

  return (
    <View style={styles.container}>
        <Header />

      <View style={styles.contentContainer}>
        <View style={{ paddingHorizontal: 20 }}>
          <View style={styles.searchContainer}>
            <TouchableOpacity onPress={()=> navigation.navigate('GroupSelection')}>
            <Icon name="search" size={20} color="#888" style={styles.searchIcon} />
            </TouchableOpacity>
            <TextInput
              style={styles.searchInput}
              placeholder="Search..."
              placeholderTextColor="#888"
              value={searchQuery}
              onChangeText={setSearchQuery}
            />
          </View>
        </View>

        {archivedConnections.length > 0 && renderArchivedSection()}

        <FlatList
          data={searchQuery ? filteredConnections : connections}
          renderItem={renderChatItem}
          keyExtractor={item => item.id}
          style={styles.chatContainer}
          showsVerticalScrollIndicator={false}
        />
      </View>
      {/* <BottomNavigationBar navigation={navigation} /> */}
    </View>
  );
};

// Styles
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F9F9F9',
    paddingVertical: 40,
  },
  actionButtonsContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  archiveButton: {
    backgroundColor: '#FF5722',
    justifyContent: 'center',
    alignItems: 'center',
    width: 60,
    height: '100%',
  },
  deleteButton: {
    backgroundColor: '#D32F2F',
    justifyContent: 'center',
    alignItems: 'center',
    width: 60,
    height: '100%',
  },
  moreOptionsButton: {
    backgroundColor: '#757575',
    justifyContent: 'center',
    alignItems: 'center',
    width: 60,
    height: '100%',
  },
  headerText: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 20,
    backgroundColor: 'white',
    paddingVertical: 10,
  },
  contentContainer: {
    flex: 1,
    paddingBottom: 20,
  },
  searchContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#E0E0E0',
    borderRadius: 8,
    paddingHorizontal: 10,
    paddingVertical: 10,
    marginBottom: 20,
  },
  searchIcon: {
    marginRight: 10,
  },
  searchInput: {
    flex: 1,
    fontSize: 16,
    color: '#000',
  },
  archiveSection: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 10,
    backgroundColor: '#E0E0E0',
  },
  archiveText: {
    marginLeft: 10,
    fontSize: 16,
    color: '#000',
  },
  chatContainer: {
    flex: 1,
  },
  chatItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 15,
    backgroundColor: '#FFF',
    borderBottomWidth: 1,
    borderBottomColor: '#E0E0E0',
  },
  profilePicture: {
    width: 50,
    height: 50,
    borderRadius: 10,
    marginRight: 15,
  },
  chatContent: {
    flex: 1,
  },
  chatHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  userName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#000',
  },
  chatTime: {
    fontSize: 12,
    color: '#888',
  },
  lastMessage: {
    fontSize: 14,
    color: '#888',
    marginTop: 4,
  },
});

export default Chats;
